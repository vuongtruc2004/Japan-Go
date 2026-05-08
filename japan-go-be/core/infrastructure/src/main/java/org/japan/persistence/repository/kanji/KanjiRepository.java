package org.japan.persistence.repository.kanji;

import org.japan.entity.kanji.KanjiEntity;
import org.japan.persistence.repository.base.BaseRepository;
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
