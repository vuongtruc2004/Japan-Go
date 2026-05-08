package org.japan.repositories.kanji;

import org.japan.entity.kanji.KanjiEntity;
import org.japan.entity.kanji.SinoVietnameseEntity;
import org.japan.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SinoVietnameseRepository extends BaseRepository<SinoVietnameseEntity> {
    Optional<SinoVietnameseEntity> findByKanji_KanjiCharacterAndReadingText(String kanjiKanjiCharacter, String readingText);

    List<SinoVietnameseEntity> findAllByKanjiInAndReadingTextIn(Collection<KanjiEntity> kanjis, Collection<String> readingTexts);
}
