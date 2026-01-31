package com.nass.infrastructure.repositories.kanji;

import com.nass.infrastructure.entities.kanji.KunyomiEntity;
import com.nass.infrastructure.repositories.base.BaseRepository;

import java.util.Optional;

public interface KunyomiRepository extends BaseRepository<KunyomiEntity, Long> {
    Optional<KunyomiEntity> findByReadingText(String readingText);
}
