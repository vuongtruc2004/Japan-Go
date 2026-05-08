package org.japan.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.japan.dto.response.sentence.SentenceResponse;
import org.japan.entity.grammar.SentenceEntity;
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
