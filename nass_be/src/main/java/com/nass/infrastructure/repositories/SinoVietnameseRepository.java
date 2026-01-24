package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.kanji.SinoVietnameseEntity;
import com.nass.infrastructure.repositories.base.BaseJpaSpecificationRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SinoVietnameseRepository extends BaseJpaSpecificationRepository<SinoVietnameseEntity, Long> {
    Optional<SinoVietnameseEntity> findByKanji_KanjiCharacterAndReadingText(String kanjiKanjiCharacter, String readingText);
}
