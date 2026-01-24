package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.common.SentenceEntity;
import com.nass.infrastructure.repositories.base.BaseJpaSpecificationRepository;

import java.util.List;

public interface SentenceRepository extends BaseJpaSpecificationRepository<SentenceEntity, Long> {
    List<SentenceEntity> findAllByGrammarExampleNotNull();
}
