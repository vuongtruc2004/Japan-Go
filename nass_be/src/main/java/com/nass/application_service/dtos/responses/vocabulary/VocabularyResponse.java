package com.nass.application_service.dtos.responses.vocabulary;

import com.nass.infrastructure.entities.vocabulary.VocabularyReadingEntity;
import com.nass.infrastructure.entities.vocabulary.VocabularyTypeEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VocabularyResponse {
    String japanese;

    String sinoVietnamese;

    @Builder.Default
    List<VocabularyTypeEntity> vocabularyTypes = new ArrayList<>();

    @Builder.Default
    List<VocabularyReadingEntity> vocabularyReadings = new ArrayList<>();
}
