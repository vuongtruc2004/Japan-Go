package com.nass.business.kanji.mapper;

import com.nass.business.kanji.dto.response.KanjiPageResponse;
import com.nass.business.kanji.dto.response.KanjiResponse;
import com.nass.business.kanji.entity.KanjiEntity;
import com.nass.business.kanji.entity.KanjiPageEntity;
import com.nass.business.kanji.entity.KunyomiEntity;
import com.nass.business.kanji.entity.OnyomiEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KanjiDtoMapper {
    private final ModelMapper modelMapper;

    public KanjiResponse kanjiEntityToKanjiResponse(KanjiEntity kanjiEntity) {
        KanjiResponse kanjiResponse = modelMapper.map(kanjiEntity, KanjiResponse.class);
        kanjiResponse.setMainSinoVietnamese(kanjiEntity.getMainSinoVietnamese().getReadingText());
        kanjiResponse.setOnyomiList(kanjiEntity.getOnyomiList().stream().map(OnyomiEntity::getReadingText).toList());
        kanjiResponse.setKunyomiList(kanjiEntity.getKunyomiList().stream().map(KunyomiEntity::getReadingText).toList());
        return kanjiResponse;
    }

    public KanjiPageResponse kanjiPageEntityToKanjiPageResponse(KanjiPageEntity kanjiPageEntity) {
        KanjiPageResponse kanjiPageResponse = modelMapper.map(kanjiPageEntity, KanjiPageResponse.class);
        kanjiPageResponse.setMainKanji(kanjiEntityToKanjiResponse(kanjiPageEntity.getMainKanji()));
        return kanjiPageResponse;
    }
}
