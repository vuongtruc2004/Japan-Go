package com.nass.application_service.importers;

import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.helpers.ParseHelper;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.FileMessage;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.entities.kanji.KunyomiEntity;
import com.nass.infrastructure.entities.kanji.MeaningEntity;
import com.nass.infrastructure.entities.kanji.OnyomiEntity;
import com.nass.infrastructure.repositories.KanjiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.*;

@Component
@RequiredArgsConstructor
public class KanjiDicXmlImporter {
    private final FileValidator fileValidator;
    private final ParseHelper parseHelper;
    private final KanjiRepository kanjiRepository;

    public List<KanjiEntity> importKanjiFromKanjidic(MultipartFile kanjidicFile, MultipartFile kanjijlptFile) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        try (InputStream inputStream = kanjidicFile.getInputStream()) {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream);

            List<KanjiEntity> list = new ArrayList<>();
            Map<String, Integer> kanjiInJlpt = getKanjiByJLPTLevel(kanjijlptFile);
            KanjiEntity kanjiEntity = null;

            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();

                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    kanjiEntity = enrichKanjiProperties(startElement, xmlEventReader, kanjiEntity);
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    String tagName = endElement.getName().getLocalPart();
                    if ("character".equals(tagName)) {
                        addKanjiIntoList(kanjiEntity, list, kanjiInJlpt);
                        kanjiEntity = null;
                    }
                }
            }
            return list;
        } catch (Exception e) {
            throw new FileNotValidException("Method: getKanjiFromKANJIDIC2File() => " + e.getMessage());
        }
    }

    private KanjiEntity enrichKanjiProperties(StartElement startElement, XMLEventReader xmlEventReader, KanjiEntity kanjiEntity) throws XMLStreamException {
        String tag = startElement.getName().getLocalPart();
        switch (tag) {
            case "character":
                kanjiEntity = new KanjiEntity();
                break;
            case "literal":
                if (kanjiEntity != null)
                    kanjiEntity.setKanji(xmlEventReader.getElementText().trim());
                break;
            case "cp_value":
                handleCpValueTag(kanjiEntity, startElement, xmlEventReader);
                break;
            case "grade":
                if (kanjiEntity != null)
                    kanjiEntity.setGrade(parseHelper.parseStringToInteger(xmlEventReader.getElementText()));
                break;
            case "stroke_count":
                if (kanjiEntity != null)
                    kanjiEntity.setStrokeCount(parseHelper.parseStringToInteger(xmlEventReader.getElementText()));
                break;
            case "freq":
                if (kanjiEntity != null)
                    kanjiEntity.setFrequency(parseHelper.parseStringToInteger(xmlEventReader.getElementText()));
                break;
            case "reading":
                handleReadingTag(kanjiEntity, startElement, xmlEventReader);
                break;
            case "meaning":
                handleMeaningTag(kanjiEntity, startElement, xmlEventReader);
                break;
            default:
                break;
        }
        return kanjiEntity;
    }

    private void handleCpValueTag(KanjiEntity kanjiEntity, StartElement startElement, XMLEventReader xmlEventReader) throws XMLStreamException {
        if (kanjiEntity != null) {
            Attribute cpType = startElement.getAttributeByName(new QName("cp_type"));
            if ("ucs".equals(cpType.getValue())) {
                kanjiEntity.setUnicode("U+" + xmlEventReader.getElementText().trim());
            }
        }
    }

    private void handleReadingTag(KanjiEntity kanjiEntity, StartElement startElement, XMLEventReader xmlEventReader) throws XMLStreamException {
        if (kanjiEntity != null) {
            Attribute readingType = startElement.getAttributeByName(new QName("r_type"));
            String value = xmlEventReader.getElementText().trim();
            if (!value.isEmpty()) {
                if (readingType.getValue().equals("ja_on")) {
                    OnyomiEntity onyomi = OnyomiEntity.builder()
                            .onyomi(value)
                            .kanji(kanjiEntity)
                            .build();
                    kanjiEntity.getOnyomi().add(onyomi);
                } else if (readingType.getValue().equals("ja_kun")) {
                    KunyomiEntity kunyomi = KunyomiEntity.builder()
                            .kunyomi(value)
                            .kanji(kanjiEntity)
                            .build();
                    kanjiEntity.getKunyomi().add(kunyomi);
                }
            }
        }
    }

    private void handleMeaningTag(KanjiEntity kanjiEntity, StartElement startElement, XMLEventReader xmlEventReader) throws XMLStreamException {
        if (kanjiEntity != null) {
            Attribute meaningLang = startElement.getAttributeByName(new QName("m_lang"));
            String value = xmlEventReader.getElementText().trim();
            if (meaningLang == null && !value.isEmpty()) {
                MeaningEntity meaning = MeaningEntity.builder()
                        .meaning(value)
                        .kanji(kanjiEntity)
                        .build();
                kanjiEntity.getMeanings().add(meaning);
            }
        }
    }

    private void addKanjiIntoList(KanjiEntity kanjiEntity, List<KanjiEntity> list, Map<String, Integer> kanjiInJlpt) {
        if (kanjiEntity != null && kanjiEntity.getUnicode() != null) {
            if (kanjiRepository.existsByUnicode(kanjiEntity.getUnicode())) {
                throw new FileNotValidException("Unicode " + kanjiEntity.getUnicode() + " already exists");
            }
            Integer jlptLevel = kanjiInJlpt.get(kanjiEntity.getKanji());
            if (jlptLevel != null) {
                kanjiEntity.setJlptLevel(jlptLevel);
            }
            list.add(kanjiEntity);
        }
    }

    private Map<String, Integer> getKanjiByJLPTLevel(MultipartFile file) {
        if (!fileValidator.isJSONFile(file)) {
            throw new FileNotValidException(
                    FileMessage.IS_NOT_XML_FILE_DEV,
                    FileMessage.IS_NOT_XML_FILE_USER
            );
        }
        try (InputStream inputStream = file.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(inputStream);

            if (!root.isObject()) {
                throw new FileNotValidException("");
            }
            Map<String, Integer> map = new HashMap<>();
            Set<Map.Entry<String, JsonNode>> fields = root.properties();

            for (Map.Entry<String, JsonNode> field : fields) {
                String jlptLevel = field.getKey();
                String[] kanjiChars = field.getValue().asString().split(",");
                for (String kanjiChar : kanjiChars) {
                    map.put(kanjiChar, parseHelper.parseStringToInteger(jlptLevel));
                }
            }

            return map;
        } catch (Exception e) {
            throw new FileNotValidException("Method: getKanjiByJLPTLevel() => " + e.getMessage());
        }
    }
}
