package com.japan_go_be.business.dto.mappers;

import com.japan_go_be.business.dto.responses.kanji.VocabularyResponse;
import com.japan_go_be.business.helpers.kanji.SinoVietnameseHelper;
import com.japan_go_be.infrastructure.entities.kanji.VocabularyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VocabularyDtoMapper {
    private final SinoVietnameseHelper sinoVietnameseHelper;

    public VocabularyResponse vocabularyEntityToVocabularyResponse(VocabularyEntity vocabulary) {
        String sinoVietnamese = sinoVietnameseHelper.getSinoVietnameseOfKanji(vocabulary.getJapanese());

        return VocabularyResponse.builder()
                .id(vocabulary.getId())
                .japanese(vocabulary.getJapanese())
                .sinoVietnamese(sinoVietnamese)
                .reading(vocabulary.getReading())
                .meaning(vocabulary.getMeaning())
                .note(vocabulary.getNote())
                .build();
    }
}
