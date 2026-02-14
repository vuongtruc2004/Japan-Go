package com.japan_go_be.features.kanji.repository;

import com.japan_go_be.common.persistence.BaseRepository;
import com.japan_go_be.features.kanji.entity.KanjiEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface KanjiRepository extends BaseRepository<KanjiEntity> {
    List<KanjiEntity> findAllByUnicodeIn(Collection<String> unicodes);

    Optional<KanjiEntity> findByKanjiCharacter(String kanjiCharacter);

    boolean existsByUnicode(String unicode);

    List<KanjiEntity> findAllByKanjiCharacterIn(Collection<String> kanjiCharacters);
}
