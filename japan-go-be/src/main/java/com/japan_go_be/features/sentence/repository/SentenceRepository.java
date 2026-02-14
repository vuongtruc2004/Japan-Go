package com.japan_go_be.features.sentence.repository;

import com.japan_go_be.common.persistence.BaseRepository;
import com.japan_go_be.features.sentence.entity.SentenceEntity;

import java.util.List;

public interface SentenceRepository extends BaseRepository<SentenceEntity> {
    List<SentenceEntity> findAllByGrammarExampleNotNull();
}
