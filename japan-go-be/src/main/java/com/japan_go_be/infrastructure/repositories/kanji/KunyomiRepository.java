package com.japan_go_be.infrastructure.repositories.kanji;

import com.japan_go_be.infrastructure.repositories.base.BaseRepository;
import com.japan_go_be.infrastructure.entities.kanji.KunyomiEntity;

import java.util.Optional;

public interface KunyomiRepository extends BaseRepository<KunyomiEntity> {
    Optional<KunyomiEntity> findByReadingText(String readingText);
}
