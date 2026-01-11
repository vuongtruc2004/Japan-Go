package com.nass.application_service.importers;

import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.helpers.ParseHelper;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.FileMessage;
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

    public Map<String, Integer> importKanjiByJlptLevel(MultipartFile file) {
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
                String[] kanjiCharacters = field.getValue().asString().split(",");
                for (String kanjiCharacter : kanjiCharacters) {
                    if (!kanjiCharacter.isBlank()) {
                        map.put(kanjiCharacter.trim(), parseHelper.parseStringToInteger(jlptLevel));
                    }
                }
            }

            return map;
        } catch (Exception e) {
            throw new FileNotValidException("Method: getKanjiByJLPTLevel() => " + e.getMessage());
        }
    }

}
