package com.japan_go_be.features.sentence.mapper;

import com.japan_go_be.features.sentence.dto.response.SentenceResponse;
import com.japan_go_be.features.sentence.entity.SentenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SentenceDtoMapper {
    public SentenceResponse sentenceEntityToSentenceResponseDetails(SentenceEntity sentenceEntity) {
        return SentenceResponse.builder()
                .id(sentenceEntity.getId())
                .japaneseRaw(sentenceEntity.getJapaneseRaw())
                .englishRaw(sentenceEntity.getEnglishRaw())
                .vietnameseRaw(sentenceEntity.getVietnameseRaw())
                .japaneseHtml(sentenceEntity.getJapaneseHtml())
                .englishHtml(sentenceEntity.getEnglishHtml())
                .vietnameseHtml(sentenceEntity.getVietnameseHtml())
                .build();
    }
}
