package com.japan_go_be.features.vocabulary.mapper;

import com.japan_go_be.features.vocabulary.dto.response.VocabularyResponse;
import com.japan_go_be.features.vocabulary.entity.VocabularyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VocabularyDtoMapper {
    public VocabularyResponse vocabularyEntityToVocabularyResponse(VocabularyEntity vocabulary) {
        return VocabularyResponse.builder()
                .id(vocabulary.getId())
                .japanese(vocabulary.getJapanese())
                .reading(vocabulary.getReading())
                .meaning(vocabulary.getMeaning())
                .note(vocabulary.getNote())
                .build();
    }
}
