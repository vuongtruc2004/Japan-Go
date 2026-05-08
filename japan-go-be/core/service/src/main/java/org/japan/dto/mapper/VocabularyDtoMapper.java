package org.japan.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.japan.dto.response.kanji.VocabularyResponse;
import org.japan.entity.kanji.VocabularyEntity;
import org.japan.helpers.kanji.SinoVietnameseHelper;
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
