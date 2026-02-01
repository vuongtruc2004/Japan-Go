package com.nass.business.kanji.repository;

import com.nass.business.kanji.entity.OnyomiEntity;
import com.nass.common.persistence.BaseRepository;

import java.util.Optional;

public interface OnyomiRepository extends BaseRepository<OnyomiEntity, Long> {
    Optional<OnyomiEntity> findByReadingText(String readingText);
}
