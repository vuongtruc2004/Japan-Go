package com.nass.business.kanji.repository;

import com.nass.business.kanji.entity.KunyomiEntity;
import com.nass.common.persistence.BaseRepository;

import java.util.Optional;

public interface KunyomiRepository extends BaseRepository<KunyomiEntity, Long> {
    Optional<KunyomiEntity> findByReadingText(String readingText);
}
