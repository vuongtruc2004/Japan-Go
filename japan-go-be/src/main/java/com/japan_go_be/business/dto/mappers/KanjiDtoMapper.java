package com.japan_go_be.business.dto.mappers;

import com.japan_go_be.business.dto.responses.kanji.KanjiPageResponse;
import com.japan_go_be.business.dto.responses.kanji.KanjiResponse;
import com.japan_go_be.business.entries.KanjiDicEntry;
import com.japan_go_be.infrastructure.entities.kanji.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KanjiDtoMapper {

    private final VocabularyDtoMapper vocabularyDtoMapper;

    public KanjiEntity kanjiDicEntryToKanjiEntity(KanjiDicEntry kanjiDicEntry) {
        return KanjiEntity.builder()
                .kanjiCharacter(kanjiDicEntry.getKanjiCharacter())
                .unicode(kanjiDicEntry.getUnicode())
                .kanjiVg(kanjiDicEntry.getKanjiVg())
                .grade(kanjiDicEntry.getGrade())
                .strokeCount(kanjiDicEntry.getStrokeCount())
                .frequency(kanjiDicEntry.getFrequency())
                .jlptLevel(kanjiDicEntry.getJlptLevel())
                .build();
    }

    public KanjiResponse kanjiEntityToKanjiResponse(KanjiEntity kanjiEntity) {
        KanjiResponse kanjiResponse = KanjiResponse.builder()
                .kanjiCharacter(kanjiEntity.getKanjiCharacter())
                .unicode(kanjiEntity.getUnicode())
                .kanjiVg(kanjiEntity.getKanjiVg())
                .grade(kanjiEntity.getGrade())
                .strokeCount(kanjiEntity.getStrokeCount())
                .frequency(kanjiEntity.getFrequency())
                .jlptLevel(kanjiEntity.getJlptLevel())
                .build();

        SinoVietnameseEntity mainSinoVietnamese = kanjiEntity.getMainSinoVietnamese();
        if (mainSinoVietnamese != null) kanjiResponse.setMainSinoVietnamese(mainSinoVietnamese.getReadingText());

        kanjiResponse.setOnyomiList(kanjiEntity.getOnyomiList().stream().map(OnyomiEntity::getReadingText).toList());
        kanjiResponse.setKunyomiList(kanjiEntity.getKunyomiList().stream().map(KunyomiEntity::getReadingText).toList());
        return kanjiResponse;
    }

    public KanjiPageResponse kanjiPageEntityToKanjiPageResponse(KanjiPageEntity kanjiPageEntity) {
        KanjiPageResponse kanjiPageResponse = KanjiPageResponse.builder()
                .id(kanjiPageEntity.getId())
                .build();

        kanjiPageResponse.setMainKanji(kanjiEntityToKanjiResponse(kanjiPageEntity.getMainKanji()));
        kanjiPageResponse.setVocabularies(kanjiPageEntity
                .getVocabularies()
                .stream()
                .map(vocabularyDtoMapper::vocabularyEntityToVocabularyResponse)
                .toList());

        return kanjiPageResponse;
    }
}
