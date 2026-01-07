package com.nass.application_service.validators;

import com.nass.application_service.exceptions.BadRequestException;
import com.nass.contract.constants.FileMessage;
import com.nass.contract.constants.VocabularyMessage;
import org.springframework.stereotype.Service;

@Service
public class VocabularyValidator {

    public boolean validateVocabulary(VocabularyEntity vocabulary, int line) {
        if (vocabulary.getVietnamese().isBlank()) {
            throw new BadRequestException(
                    VocabularyMessage.VIETNAMESE_BLANK_DEV,
                    VocabularyMessage.VIETNAMESE_BLANK_USER,
                    FileMessage.FILE_ERROR_AT_LINE_USER(line)
            );
        }
        if (vocabulary.getFurigana().isBlank()) {
            throw new BadRequestException(
                    VocabularyMessage.FURIGANA_BLANK_DEV,
                    VocabularyMessage.FURIGANA_BLANK_USER,
                    FileMessage.FILE_ERROR_AT_LINE_USER(line)
            );
        }
        return true;
    }
}
