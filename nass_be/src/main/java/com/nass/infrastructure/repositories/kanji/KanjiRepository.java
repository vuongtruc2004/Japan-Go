package com.nass.infrastructure.repositories.kanji;

import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface KanjiRepository extends BaseRepository<KanjiEntity, Integer> {
    List<KanjiEntity> findAllByUnicodeIn(Collection<String> unicodes);

    Optional<KanjiEntity> findByKanjiCharacter(String kanjiCharacter);

    boolean existsByUnicode(String unicode);

    List<KanjiEntity> findAllByKanjiCharacterIn(Collection<String> kanjiCharacters);
}
