package com.japan_go_be.features.kanji.mapper;

import com.japan_go_be.features.kanji.dto.response.KanjiPageResponse;
import com.japan_go_be.features.kanji.dto.response.KanjiResponse;
import com.japan_go_be.features.kanji.entity.*;
import com.japan_go_be.features.kanji.entry.KanjiDicEntry;
import com.japan_go_be.features.vocabulary.mapper.VocabularyDtoMapper;
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
