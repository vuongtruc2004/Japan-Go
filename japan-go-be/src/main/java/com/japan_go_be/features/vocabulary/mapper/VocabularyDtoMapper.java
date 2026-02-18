package com.japan_go_be.features.vocabulary.mapper;

import com.japan_go_be.features.kanji.helper.SinoVietnameseHelper;
import com.japan_go_be.features.vocabulary.dto.response.VocabularyResponse;
import com.japan_go_be.features.vocabulary.entity.VocabularyEntity;
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
