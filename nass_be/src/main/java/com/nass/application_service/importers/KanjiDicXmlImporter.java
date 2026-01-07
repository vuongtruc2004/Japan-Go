package com.nass.application_service.importers;

import com.nass.application_service.dto.entries.KanjiEntry;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.helpers.ParseHelper;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.FileMessage;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class KanjiDicXmlImporter {
    private final FileValidator fileValidator;
    private final ParseHelper parseHelper;

    public Map<String, KanjiEntry> getKanjiFromKANJIDIC2File(MultipartFile kanjidic2File, MultipartFile kanjijlptFile) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        try (InputStream inputStream = kanjidic2File.getInputStream()) {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream);

            Map<String, KanjiEntry> map = new HashMap<>();
            Map<String, Integer> kanjiInJlpt = getKanjiByJLPTLevel(kanjijlptFile);
            KanjiEntry currentKanjiEntry = null;

            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();

                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    currentKanjiEntry = enrichKanjiProperties(startElement, xmlEventReader, currentKanjiEntry);
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    String tagName = endElement.getName().getLocalPart();
                    if ("character".equals(tagName)) {
                        putKanjiIntoMap(currentKanjiEntry, map, kanjiInJlpt);
                        currentKanjiEntry = null;
                    }
                }
            }
            return map;
        } catch (Exception e) {
            throw new FileNotValidException("Method: getKanjiFromKANJIDIC2File() => " + e.getMessage());
        }
    }

    private KanjiEntry enrichKanjiProperties(StartElement startElement, XMLEventReader xmlEventReader, KanjiEntry currentKanjiEntry) throws XMLStreamException {
        String tag = startElement.getName().getLocalPart();
        switch (tag) {
            case "character":
                currentKanjiEntry = new KanjiEntry();
                break;
            case "literal":
                if (currentKanjiEntry != null)
                    currentKanjiEntry.setKanji(xmlEventReader.getElementText().trim());
                break;
            case "cp_value":
                handleCpValueTag(currentKanjiEntry, startElement, xmlEventReader);
                break;
            case "grade":
                if (currentKanjiEntry != null)
                    currentKanjiEntry.setGrade(parseHelper.parseStringToInteger(xmlEventReader.getElementText()));
                break;
            case "stroke_count":
                if (currentKanjiEntry != null)
                    currentKanjiEntry.setStrokeCount(parseHelper.parseStringToInteger(xmlEventReader.getElementText()));
                break;
            case "freq":
                if (currentKanjiEntry != null)
                    currentKanjiEntry.setFrequency(parseHelper.parseStringToInteger(xmlEventReader.getElementText()));
                break;
            case "reading":
                handleReadingTag(currentKanjiEntry, startElement, xmlEventReader);
                break;
            case "meaning":
                handleMeaningTag(currentKanjiEntry, startElement, xmlEventReader);
                break;
            default:
                break;
        }
        return currentKanjiEntry;
    }

    private void handleCpValueTag(KanjiEntry currentKanjiEntry, StartElement startElement, XMLEventReader xmlEventReader) throws XMLStreamException {
        if (currentKanjiEntry != null) {
            Attribute cpType = startElement.getAttributeByName(new QName("cp_type"));
            if ("ucs".equals(cpType.getValue())) {
                currentKanjiEntry.setUnicode("U+" + xmlEventReader.getElementText().trim());
            }
        }
    }

    private void handleReadingTag(KanjiEntry currentKanjiEntry, StartElement startElement, XMLEventReader xmlEventReader) throws XMLStreamException {
        if (currentKanjiEntry != null) {
            Attribute readingType = startElement.getAttributeByName(new QName("r_type"));
            String value = xmlEventReader.getElementText().trim();
            if (!value.isEmpty()) {
                if (readingType.getValue().equals("ja_on")) {
                    currentKanjiEntry.getOnyomi().add(value);
                } else if (readingType.getValue().equals("ja_kun")) {
                    currentKanjiEntry.getKunyomi().add(value);
                }
            }
        }
    }

    private void handleMeaningTag(KanjiEntry currentKanjiEntry, StartElement startElement, XMLEventReader xmlEventReader) throws XMLStreamException {
        if (currentKanjiEntry != null) {
            Attribute meaningLang = startElement.getAttributeByName(new QName("m_lang"));
            String value = xmlEventReader.getElementText().trim();
            if (meaningLang == null && !value.isEmpty()) {
                currentKanjiEntry.getMeanings().add(value);
            }
        }
    }

    private void putKanjiIntoMap(KanjiEntry currentKanjiEntry, Map<String, KanjiEntry> map, Map<String, Integer> kanjiInJlpt) {
        if (currentKanjiEntry != null && currentKanjiEntry.getUnicode() != null) {
            Integer jlptLevel = kanjiInJlpt.get(currentKanjiEntry.getKanji());
            if (jlptLevel != null) {
                currentKanjiEntry.setJlptLevel(jlptLevel);
            }
            map.put(currentKanjiEntry.getUnicode(), currentKanjiEntry);
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
