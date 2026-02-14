package com.japan_go_be.features.kanji.repository;

import com.japan_go_be.common.persistence.BaseRepository;
import com.japan_go_be.features.kanji.entity.OnyomiEntity;

import java.util.Optional;

public interface OnyomiRepository extends BaseRepository<OnyomiEntity> {
    Optional<OnyomiEntity> findByReadingText(String readingText);
}
