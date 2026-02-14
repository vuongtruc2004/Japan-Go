package com.japan_go_be.features.kanji.repository;

import com.japan_go_be.common.persistence.BaseRepository;
import com.japan_go_be.features.kanji.entity.KunyomiEntity;

import java.util.Optional;

public interface KunyomiRepository extends BaseRepository<KunyomiEntity> {
    Optional<KunyomiEntity> findByReadingText(String readingText);
}
