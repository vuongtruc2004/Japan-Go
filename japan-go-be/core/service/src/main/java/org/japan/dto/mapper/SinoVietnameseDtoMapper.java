package org.japan.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.japan.dto.response.kanji.SinoVietnameseResponse;
import org.japan.entity.kanji.SinoVietnameseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SinoVietnameseDtoMapper {
    public SinoVietnameseResponse mapSinoVietnameseEntityToSinoVietnameseResponse(SinoVietnameseEntity entity) {
        return SinoVietnameseResponse.builder()
                .id(entity.getId())
                .readingText(entity.getReadingText())
                .build();
    }
}
