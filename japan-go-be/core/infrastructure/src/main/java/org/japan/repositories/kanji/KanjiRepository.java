package org.japan.repositories.kanji;

import org.japan.entity.kanji.KanjiEntity;
import org.japan.repositories.base.BaseRepository;
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

    List<KanjiEntity> findAllByJlptLevel(Integer jlptLevel);
}
