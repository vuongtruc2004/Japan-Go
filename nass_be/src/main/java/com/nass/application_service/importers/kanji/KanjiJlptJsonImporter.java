package com.nass.application_service.importers.kanji;

import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.helpers.common.ParseHelper;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.messages.common.FileMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class KanjiJlptJsonImporter {
    private final FileValidator fileValidator;
    private final ParseHelper parseHelper;
    private final I18nService i18nService;

    public Map<String, Integer> importKanjiByJlptLevel(MultipartFile file) {
        if (!fileValidator.isJSONFile(file)) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_NOT_JSON),
                    i18nService.translation(FileMessage.FILE_NOT_JSON)
            );
        }
        try (InputStream inputStream = file.getInputStream()) {
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
        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }

}
