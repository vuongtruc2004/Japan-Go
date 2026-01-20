package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.grammar.SentenceEntity;
import com.nass.infrastructure.repositories.base.IJpaSpecificationRepository;

import java.util.List;

public interface SentenceRepository extends IJpaSpecificationRepository<SentenceEntity, Long> {
    List<SentenceEntity> findAllByExampleNotNull();
}
