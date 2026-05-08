package org.japan.repositories.kanji;

import org.japan.entity.kanji.OnyomiEntity;
import org.japan.repositories.base.BaseRepository;

import java.util.Optional;

public interface OnyomiRepository extends BaseRepository<OnyomiEntity> {
    Optional<OnyomiEntity> findByReadingText(String readingText);
}
