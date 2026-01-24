package com.nass.application_service.importers.kanji;

import com.nass.application_service.dto.entries.SinoVietnameseEntry;
import com.nass.application_service.dto.entries.SinoVietnameseMeaningEntry;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.contract.constants.messages.common.FileMessage;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.entities.kanji.SinoVietnameseEntity;
import com.nass.infrastructure.entities.kanji.SinoVietnameseMeaningEntity;
import com.nass.infrastructure.repositories.KanjiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class SinoVietnameseJsonImporter {
    private final KanjiRepository kanjiRepository;
    private final I18nService i18nService;

    public void importSinoVietnamese(MultipartFile sinoVietnameseFile, Map<String, List<SinoVietnameseEntry>> sinoVietnameseEntryMap) {
        try (InputStream inputStream = sinoVietnameseFile.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(inputStream);

            for (JsonNode node : root) {
                if (node.isArray() && node.size() >= 6) {
                    JsonNode lastNode = node.get(node.size() - 1);

                    if (lastNode.isObject() && lastNode.has("Unicode")) {
                        String unicode = lastNode.get("Unicode").asString().trim().toUpperCase();
                        sinoVietnameseEntryMap.compute(unicode, (key, value) -> {
                            if (value != null) {
                                throw new FileNotValidException(
                                        i18nService.translation(FileMessage.FILE_ERROR_FORMAT),
                                        i18nService.translation(FileMessage.FILE_ERROR_FORMAT)
                                );
                            }
                            return convertJsonNodeToSinoVietnameseEntryList(node.get(1), node.get(4));
                        });
                    }
                }
            }
        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }

    public List<KanjiEntity> updateKanjiWithSinoVietnamese(Map<String, List<SinoVietnameseEntry>> sinoVietnameseEntryMap) {
        List<KanjiEntity> kanjiEntities = kanjiRepository.findAllByUnicodeIn(sinoVietnameseEntryMap.keySet());
        for (KanjiEntity kanjiEntity : kanjiEntities) {
            List<SinoVietnameseEntity> sinoVietnameseEntities = new ArrayList<>();

            List<SinoVietnameseEntry> sinoVietnameseEntries = sinoVietnameseEntryMap.get(kanjiEntity.getUnicode());
            if (sinoVietnameseEntries != null) {
                for (SinoVietnameseEntry sinoVietnameseEntry : sinoVietnameseEntries) {
                    SinoVietnameseEntity sinoVietnameseEntity = SinoVietnameseEntity.builder()
                            .readingText(sinoVietnameseEntry.getReadingText())
                            .kanji(kanjiEntity)
                            .build();

                    for (SinoVietnameseMeaningEntry sinoVietnameseMeaningEntry : sinoVietnameseEntry.getSinoVietnameseMeaningList()) {
                        sinoVietnameseEntity.getSinoVietnameseMeaningList().add(SinoVietnameseMeaningEntity.builder()
                                .readingText(sinoVietnameseMeaningEntry.getReadingText())
                                .sinoVietnamese(sinoVietnameseEntity)
                                .build());
                    }

                    sinoVietnameseEntities.add(sinoVietnameseEntity);

                }
            } else {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_ERROR_FORMAT),
                        i18nService.translation(FileMessage.FILE_ERROR_FORMAT)
                );
            }

            kanjiEntity.getSinoVietnameseList().addAll(sinoVietnameseEntities);
        }
        return kanjiEntities;
    }

    private List<SinoVietnameseEntry> convertJsonNodeToSinoVietnameseEntryList(JsonNode firstNode, JsonNode fourthNode) {
        if (!fourthNode.isArray())
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR_FORMAT),
                    i18nService.translation(FileMessage.FILE_ERROR_FORMAT)
            );

        String[] sinoVietnameseArray = firstNode.asString().toUpperCase().split("\\s+");
        Map<String, List<String>> sinoVietnameseMeaningMap = new HashMap<>();

        for (JsonNode node : fourthNode) {
            String value = node.asString().trim();
            /*
             ^: bắt đầu
             \\[: dấu [
             [^]]+: bất kì kí từ gì trừ dấu ]
             ]: dấu ]
             \\s+: khoảng trắng
             .+: chuỗi bất kì
             $: kết thúc
            */
            if (!value.matches("^\\[[^]]+]\\s+.+$")) {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_ERROR_FORMAT),
                        i18nService.translation(FileMessage.FILE_ERROR_FORMAT)
                );
            }
            String[] parts = value.split("]");
            String sinoVietnamese = parts[0].substring(1).trim().toUpperCase();
            String meaning = parts[1].trim();
            sinoVietnameseMeaningMap
                    .computeIfAbsent(sinoVietnamese, k -> new ArrayList<>())
                    .add(meaning);
        }

        List<SinoVietnameseEntry> sinoVietnameseEntries = new ArrayList<>();
        for (String sinoVietnamese : sinoVietnameseArray) {
            SinoVietnameseEntry sinoVietnameseEntry = SinoVietnameseEntry.builder()
                    .readingText(sinoVietnamese)
                    .build();

            List<String> sinoVietnameseMeaningList = sinoVietnameseMeaningMap.get(sinoVietnamese);
            if (sinoVietnameseMeaningList != null) {
                sinoVietnameseEntry.getSinoVietnameseMeaningList().addAll(sinoVietnameseMeaningList
                        .stream().map(readingText -> SinoVietnameseMeaningEntry.builder()
                                .readingText(readingText)
                                .build()).toList());
            }
            sinoVietnameseEntries.add(sinoVietnameseEntry);
        }
        return sinoVietnameseEntries;
    }
}
