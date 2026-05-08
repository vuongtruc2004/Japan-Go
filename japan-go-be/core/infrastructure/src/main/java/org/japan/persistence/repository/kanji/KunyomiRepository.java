package org.japan.persistence.repository.kanji;

import org.japan.entity.kanji.KunyomiEntity;
import org.japan.persistence.repository.base.BaseRepository;

import java.util.Optional;

public interface KunyomiRepository extends BaseRepository<KunyomiEntity> {
    Optional<KunyomiEntity> findByReadingText(String readingText);
}
