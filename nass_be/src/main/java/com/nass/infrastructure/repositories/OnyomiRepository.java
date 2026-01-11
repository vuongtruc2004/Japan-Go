package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.kanji.OnyomiEntity;
import com.nass.infrastructure.repositories.base.IJpaSpecificationRepository;

import java.util.Optional;

public interface OnyomiRepository extends IJpaSpecificationRepository<OnyomiEntity, Long> {
    Optional<OnyomiEntity> findByReadingText(String readingText);
}
