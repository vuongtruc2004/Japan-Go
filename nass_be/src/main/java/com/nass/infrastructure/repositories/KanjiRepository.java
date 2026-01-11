package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.repositories.base.IJpaSpecificationRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface KanjiRepository extends IJpaSpecificationRepository<KanjiEntity, Integer> {
    boolean existsByUnicode(String unicode);

    List<KanjiEntity> findAllByUnicodeIn(Collection<String> unicodes);

    List<KanjiEntity> findAllByKanjiCharacterIn(Collection<String> kanjiCharacters);

    Optional<KanjiEntity> findByKanjiCharacter(String kanjiCharacter);
}
