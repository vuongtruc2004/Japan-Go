package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.kanji.KanjiMeaningEntity;
import com.nass.infrastructure.repositories.base.BaseJpaSpecificationRepository;

import java.util.Optional;

public interface MeaningRepository extends BaseJpaSpecificationRepository<KanjiMeaningEntity, Long> {
    Optional<KanjiMeaningEntity> findByReadingText(String readingText);
}
