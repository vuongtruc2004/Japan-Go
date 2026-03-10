package com.japan_go_be.business.importers.kanji;

import com.japan_go_be.business.dto.mappers.KanjiDtoMapper;
import com.japan_go_be.business.entries.KanjiDicEntry;
import com.japan_go_be.business.exception.FileNotValidException;
import com.japan_go_be.business.exception.kanji.KanjiException;
import com.japan_go_be.business.helpers.kanji.KanjiHelper;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.contract.constants.message.FileMessage;
import com.japan_go_be.contract.constants.message.kanji.KanjiMessage;
import com.japan_go_be.contract.utils.ParseUtil;
import com.japan_go_be.infrastructure.entities.kanji.KanjiEntity;
import com.japan_go_be.infrastructure.entities.kanji.KanjiMeaningEntity;
import com.japan_go_be.infrastructure.entities.kanji.KunyomiEntity;
import com.japan_go_be.infrastructure.entities.kanji.OnyomiEntity;
import com.japan_go_be.infrastructure.repositories.kanji.KanjiMeaningRepository;
import com.japan_go_be.infrastructure.repositories.kanji.KanjiRepository;
import com.japan_go_be.infrastructure.repositories.kanji.KunyomiRepository;
import com.japan_go_be.infrastructure.repositories.kanji.OnyomiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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
public class KanjiXmlImporter {
    private final ParseUtil parseHelper;
    private final KanjiRepository kanjiRepository;
    private final KunyomiRepository kunyomiRepository;
    private final OnyomiRepository onyomiRepository;
    private final KanjiMeaningRepository kanjiMeaningRepository;
    private final I18nService i18nService;
    private final KanjiHelper kanjiHelper;
    private final KanjiDtoMapper kanjiDtoMapper;

    public List<KanjiEntity> importKanji(InputStream kanjidicInputstream, InputStream kanjijlptInputstream) {
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(kanjidicInputstream);

            List<KanjiDicEntry> list = new ArrayList<>();
            Map<String, Integer> kanjiInJlpt = importKanjiByJlptLevel(kanjijlptInputstream);
            KanjiDicEntry kanjiDicEntry = null;

            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();

                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    kanjiDicEntry = enrichKanjiProperties(startElement, xmlEventReader, kanjiDicEntry);
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    String tagName = endElement.getName().getLocalPart();
                    if ("character".equals(tagName)) {
                        addKanjiIntoList(kanjiDicEntry, list, kanjiInJlpt);
                        kanjiDicEntry = null;
                    }
                }
            }

            return loadKanjiDicEntryListToDb(list);
        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }

    private List<KanjiEntity> loadKanjiDicEntryListToDb(List<KanjiDicEntry> kanjiDicEntryList) {
        Set<String> allKunyomi = new HashSet<>();
        Set<String> allOnyomi = new HashSet<>();
        Set<String> allMeaning = new HashSet<>();

        for (KanjiDicEntry kanjiDicEntry : kanjiDicEntryList) {
            allKunyomi.addAll(kanjiDicEntry.getKunyomiSet());
            allOnyomi.addAll(kanjiDicEntry.getOnyomiSet());
            allMeaning.addAll(kanjiDicEntry.getKanjiMeaningSet());
        }

        // save all kunyomi
        Map<String, KunyomiEntity> kunyomiEntityMap = new HashMap<>();
        List<KunyomiEntity> kunyomiEntities = new ArrayList<>();
        for (String kunyomi : allKunyomi) {
            KunyomiEntity kunyomiEntity = KunyomiEntity.builder()
                    .readingText(kunyomi)
                    .build();
            kunyomiEntityMap.put(kunyomi, kunyomiEntity);
            kunyomiEntities.add(kunyomiEntity);
        }
        kunyomiRepository.saveAll(kunyomiEntities);

        // save all onyomi
        Map<String, OnyomiEntity> onyomiEntityMap = new HashMap<>();
        List<OnyomiEntity> onyomiEntities = new ArrayList<>();
        for (String onyomi : allOnyomi) {
            OnyomiEntity onyomiEntity = OnyomiEntity.builder()
                    .readingText(onyomi)
                    .build();
            onyomiEntityMap.put(onyomi, onyomiEntity);
            onyomiEntities.add(onyomiEntity);
        }
        onyomiRepository.saveAll(onyomiEntities);

        // save all meaning
        Map<String, KanjiMeaningEntity> meaningEntityMap = new HashMap<>();
        List<KanjiMeaningEntity> meaningEntities = new ArrayList<>();
        for (String meaning : allMeaning) {
            KanjiMeaningEntity kanjiMeaningEntity = KanjiMeaningEntity.builder()
                    .readingText(meaning)
                    .build();
            meaningEntityMap.put(meaning, kanjiMeaningEntity);
            meaningEntities.add(kanjiMeaningEntity);
        }
        kanjiMeaningRepository.saveAll(meaningEntities);

        // save all kanji
        List<KanjiEntity> kanjiEntities = new ArrayList<>();
        for (KanjiDicEntry kanjiDicEntry : kanjiDicEntryList) {
            KanjiEntity kanjiEntity = kanjiDtoMapper.kanjiDicEntryToKanjiEntity(kanjiDicEntry);
            for (String kunyomi : kanjiDicEntry.getKunyomiSet()) {
                kanjiEntity.getKunyomiList().add(kunyomiEntityMap.get(kunyomi));
            }
            for (String onyomi : kanjiDicEntry.getOnyomiSet()) {
                kanjiEntity.getOnyomiList().add(onyomiEntityMap.get(onyomi));
            }
            for (String meaning : kanjiDicEntry.getKanjiMeaningSet()) {
                kanjiEntity.getKanjiMeaningList().add(meaningEntityMap.get(meaning));
            }
            kanjiEntities.add(kanjiEntity);
        }

        return kanjiRepository.saveAll(kanjiEntities);
    }

    private KanjiDicEntry enrichKanjiProperties(StartElement startElement, XMLEventReader xmlEventReader, KanjiDicEntry kanjiDicEntry) throws XMLStreamException {
        String tag = startElement.getName().getLocalPart();
        switch (tag) {
            case "character":
                kanjiDicEntry = new KanjiDicEntry();
                break;
            case "literal":
                if (kanjiDicEntry != null) {
                    String value = xmlEventReader.getElementText().trim();
                    if (!value.isEmpty()) {
                        kanjiDicEntry.setKanjiVg(kanjiHelper.getSvgOfKanjiCharacter(value));
                        kanjiDicEntry.setKanjiCharacter(value);
                    }
                }
                break;
            case "cp_value":
                handleCpValueTag(kanjiDicEntry, startElement, xmlEventReader);
                break;
            case "grade":
                if (kanjiDicEntry != null)
                    kanjiDicEntry.setGrade(parseHelper.parseStringToInteger(xmlEventReader.getElementText()));
                break;
            case "stroke_count":
                if (kanjiDicEntry != null)
                    kanjiDicEntry.setStrokeCount(parseHelper.parseStringToInteger(xmlEventReader.getElementText()));
                break;
            case "freq":
                if (kanjiDicEntry != null)
                    kanjiDicEntry.setFrequency(parseHelper.parseStringToInteger(xmlEventReader.getElementText()));
                break;
            case "reading":
                handleReadingTag(kanjiDicEntry, startElement, xmlEventReader);
                break;
            case "meaning":
                handleMeaningTag(kanjiDicEntry, startElement, xmlEventReader);
                break;
            default:
                break;
        }
        return kanjiDicEntry;
    }

    private void handleCpValueTag(KanjiDicEntry kanjiDicEntry, StartElement startElement, XMLEventReader xmlEventReader) throws XMLStreamException {
        if (kanjiDicEntry != null) {
            Attribute cpType = startElement.getAttributeByName(new QName("cp_type"));
            if ("ucs".equals(cpType.getValue())) {
                kanjiDicEntry.setUnicode("U+" + xmlEventReader.getElementText().trim().toUpperCase());
            }
        }
    }

    private void handleReadingTag(KanjiDicEntry kanjiDicEntry, StartElement startElement, XMLEventReader xmlEventReader) throws XMLStreamException {
        if (kanjiDicEntry != null) {
            Attribute readingType = startElement.getAttributeByName(new QName("r_type"));
            String value = xmlEventReader.getElementText().trim();
            if (!value.isEmpty()) {
                if (readingType.getValue().equals("ja_on")) {
                    kanjiDicEntry.getOnyomiSet().add(value);
                } else if (readingType.getValue().equals("ja_kun")) {
                    kanjiDicEntry.getKunyomiSet().add(value);
                }
            }
        }
    }

    private void handleMeaningTag(KanjiDicEntry kanjiDicEntry, StartElement startElement, XMLEventReader xmlEventReader) throws XMLStreamException {
        if (kanjiDicEntry != null) {
            Attribute meaningLang = startElement.getAttributeByName(new QName("m_lang"));
            String value = xmlEventReader.getElementText().trim().toLowerCase();
            if (meaningLang == null && !value.isEmpty()) {
                kanjiDicEntry.getKanjiMeaningSet().add(value);
            }
        }
    }

    private void addKanjiIntoList(KanjiDicEntry kanjiDicEntry, List<KanjiDicEntry> list, Map<String, Integer> kanjiInJlpt) {
        if (kanjiDicEntry != null && kanjiDicEntry.getUnicode() != null) {
            if (kanjiRepository.existsByUnicode(kanjiDicEntry.getUnicode())) {
                throw new KanjiException(
                        i18nService.translation(KanjiMessage.KANJI_UNICODE_EXISTED, kanjiDicEntry.getUnicode()),
                        i18nService.translation(KanjiMessage.KANJI_UNICODE_EXISTED, kanjiDicEntry.getUnicode())
                );
            }
            Integer jlptLevel = kanjiInJlpt.get(kanjiDicEntry.getKanjiCharacter());
            if (jlptLevel != null) {
                kanjiDicEntry.setJlptLevel(jlptLevel);
            }
            list.add(kanjiDicEntry);
        }
    }

    private Map<String, Integer> importKanjiByJlptLevel(InputStream inputStream) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(inputStream);

        if (!root.isObject()) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR_FORMAT),
                    i18nService.translation(FileMessage.FILE_ERROR_FORMAT)
            );
        }
        Map<String, Integer> map = new HashMap<>();
        Set<Map.Entry<String, JsonNode>> fields = root.properties();

        for (Map.Entry<String, JsonNode> field : fields) {
            String jlptLevel = field.getKey();
            String[] kanjiCharacters = field.getValue().asString().split(",");
            for (String kanjiCharacter : kanjiCharacters) {
                if (!kanjiCharacter.isBlank()) {
                    map.put(kanjiCharacter.trim(), parseHelper.parseStringToInteger(jlptLevel));
                }
            }
        }

        return map;
    }
}
