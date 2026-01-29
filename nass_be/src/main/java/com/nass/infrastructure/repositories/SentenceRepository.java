package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.common.SentenceEntity;
import com.nass.infrastructure.repositories.base.BaseRepository;

import java.util.List;

public interface SentenceRepository extends BaseRepository<SentenceEntity, Long> {
    List<SentenceEntity> findAllByGrammarExampleNotNull();
}
