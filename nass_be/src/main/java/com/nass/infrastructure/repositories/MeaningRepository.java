package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.kanji.KanjiMeaningEntity;
import com.nass.infrastructure.repositories.base.IJpaSpecificationRepository;

import java.util.Optional;

public interface MeaningRepository extends IJpaSpecificationRepository<KanjiMeaningEntity, Long> {
    Optional<KanjiMeaningEntity> findByReadingText(String readingText);
}
