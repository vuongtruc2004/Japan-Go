package com.nass.business.sentence.repository;

import com.nass.business.sentence.entity.SentenceEntity;
import com.nass.common.persistence.BaseRepository;

import java.util.List;

public interface SentenceRepository extends BaseRepository<SentenceEntity, Long> {
    List<SentenceEntity> findAllByGrammarExampleNotNull();
}
