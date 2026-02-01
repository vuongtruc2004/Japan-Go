package com.nass.business.kanji.repository;

import com.nass.business.kanji.entity.KanjiEntity;
import com.nass.common.persistence.BaseRepository;
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
