package com.japan_go_be.infrastructure.repositories.kanji;

import com.japan_go_be.infrastructure.repositories.base.BaseRepository;
import com.japan_go_be.infrastructure.entities.kanji.KanjiEntity;
import com.japan_go_be.infrastructure.entities.kanji.SinoVietnameseEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SinoVietnameseRepository extends BaseRepository<SinoVietnameseEntity> {
    Optional<SinoVietnameseEntity> findByKanji_KanjiCharacterAndReadingText(String kanjiKanjiCharacter, String readingText);

    List<SinoVietnameseEntity> findAllByKanjiInAndReadingTextIn(Collection<KanjiEntity> kanjis, Collection<String> readingTexts);
}
