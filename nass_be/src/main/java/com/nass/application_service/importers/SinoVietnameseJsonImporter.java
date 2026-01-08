package com.nass.application_service.importers;

import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.exceptions.NotFoundException;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.FileMessage;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.entities.kanji.SinoVietnameseEntity;
import com.nass.infrastructure.repositories.KanjiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SinoVietnameseJsonImporter {
    private final FileValidator fileValidator;
    private final KanjiRepository kanjiRepository;

    public void enrichSinoVietnameseToAllKanji(MultipartFile sinoVietnameseFile, List<KanjiEntity> list) {
        if (!fileValidator.isJSONFile(sinoVietnameseFile)) {
            throw new FileNotValidException(
                    FileMessage.IS_NOT_XML_FILE_DEV,
                    FileMessage.IS_NOT_XML_FILE_USER
            );
        }
        try (InputStream inputStream = sinoVietnameseFile.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(inputStream);

            for (JsonNode node : root) {
                if (node.isArray() && node.size() > 1) {
                    JsonNode lastNode = node.get(node.size() - 1);

                    if (lastNode.isObject() && lastNode.has("Unicode")) {
                        String unicode = lastNode.get("Unicode").asString().trim();

                        KanjiEntity kanjiEntity = kanjiRepository.findByUnicode(unicode)
                                .orElseThrow(() -> new NotFoundException("Kanji not found!"));
                        if (!kanjiEntity.getSinoVietnamese().isEmpty()) {
                            throw new FileNotValidException("SinoVietnamese already exists!");
                        }

                        String[] sinoVietnameseArray = node.get(1).asString().split("\\s+");
                        for (String sinoVietnamese : sinoVietnameseArray) {
                            SinoVietnameseEntity sinoVietnameseEntity = SinoVietnameseEntity.builder()
                                    .sinoVietnamese(sinoVietnamese.toUpperCase())
                                    .kanji(kanjiEntity)
                                    .build();
                            kanjiEntity.getSinoVietnamese().add(sinoVietnameseEntity);
                            list.add(kanjiEntity);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new FileNotValidException("Method: enrichSinoVietnameseToKanjiFromBank() => " + e.getMessage());
        }
    }

}
