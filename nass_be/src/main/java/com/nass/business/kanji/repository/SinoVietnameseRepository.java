package com.nass.business.kanji.repository;

import com.nass.business.kanji.entity.KanjiEntity;
import com.nass.business.kanji.entity.SinoVietnameseEntity;
import com.nass.common.persistence.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SinoVietnameseRepository extends BaseRepository<SinoVietnameseEntity, Long> {
    Optional<SinoVietnameseEntity> findByKanji_KanjiCharacterAndReadingText(String kanjiKanjiCharacter, String readingText);

    List<SinoVietnameseEntity> findAllByKanjiInAndReadingTextIn(Collection<KanjiEntity> kanjis, Collection<String> readingTexts);
}
