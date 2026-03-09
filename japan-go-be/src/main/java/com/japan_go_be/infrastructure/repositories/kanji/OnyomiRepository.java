package com.japan_go_be.infrastructure.repositories.kanji;

import com.japan_go_be.infrastructure.repositories.base.BaseRepository;
import com.japan_go_be.infrastructure.entities.kanji.OnyomiEntity;

import java.util.Optional;

public interface OnyomiRepository extends BaseRepository<OnyomiEntity> {
    Optional<OnyomiEntity> findByReadingText(String readingText);
}
