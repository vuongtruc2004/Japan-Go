package com.japan_go_be.features.kanji.repository;

import com.japan_go_be.common.persistence.BaseRepository;
import com.japan_go_be.features.kanji.entity.KanjiEntity;
import com.japan_go_be.features.kanji.entity.SinoVietnameseEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SinoVietnameseRepository extends BaseRepository<SinoVietnameseEntity> {
    Optional<SinoVietnameseEntity> findByKanji_KanjiCharacterAndReadingText(String kanjiKanjiCharacter, String readingText);

    List<SinoVietnameseEntity> findAllByKanjiInAndReadingTextIn(Collection<KanjiEntity> kanjis, Collection<String> readingTexts);
}
