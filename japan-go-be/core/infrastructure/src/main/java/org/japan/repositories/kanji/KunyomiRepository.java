package org.japan.repositories.kanji;

import org.japan.entity.kanji.KunyomiEntity;
import org.japan.repositories.base.BaseRepository;

import java.util.Optional;

public interface KunyomiRepository extends BaseRepository<KunyomiEntity> {
    Optional<KunyomiEntity> findByReadingText(String readingText);
}
