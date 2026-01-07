package com.nass.application_service.importers;

import com.nass.application_service.dto.entries.KanjiEntry;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.FileMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SinoVietnameseJsonImporter {
    private final FileValidator fileValidator;

    public void enrichSinoVietnameseToKanjiFromBank(Map<String, KanjiEntry> map, MultipartFile sinovnFile) {
        if (!fileValidator.isJSONFile(sinovnFile)) {
            throw new FileNotValidException(
                    FileMessage.IS_NOT_XML_FILE_DEV,
                    FileMessage.IS_NOT_XML_FILE_USER
            );
        }
        try (InputStream inputStream = sinovnFile.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode nodes = objectMapper.readTree(inputStream);

            for (JsonNode node : nodes) {
                enrichSinoVietnameseToKanji(node, map);
            }
        } catch (Exception e) {
            throw new FileNotValidException("Method: enrichSinoVietnameseToKanjiFromBank() => " + e.getMessage());
        }
    }

    private void enrichSinoVietnameseToKanji(JsonNode node, Map<String, KanjiEntry> map) {
        if (node.isArray() && node.size() > 1) {
            JsonNode last = node.get(node.size() - 1);
            if (last.isObject() && last.has("Unicode")) {
                String unicode = last.get("Unicode").asString().trim();
                KanjiEntry kanjiEntry = map.get(unicode);
                if (kanjiEntry != null && kanjiEntry.getSinoVietnameses().isEmpty() && kanjiEntry.getUnicode().equals(unicode)) {
                    String sinoVietnamese = node.get(1).asString();
                    if (!sinoVietnamese.isBlank()) {
                        kanjiEntry.setSinoVietnameses(
                                Arrays.stream(sinoVietnamese.split("\\s+"))
                                        .map(s -> s.trim().toUpperCase())
                                        .toList());
                    }
                }
            }
        }
    }
}
