package org.japan.persistence.repository.kanji;

import org.japan.entity.kanji.OnyomiEntity;
import org.japan.persistence.repository.base.BaseRepository;

import java.util.Optional;

public interface OnyomiRepository extends BaseRepository<OnyomiEntity> {
    Optional<OnyomiEntity> findByReadingText(String readingText);
}
