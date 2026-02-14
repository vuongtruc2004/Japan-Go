package com.japan_go_be.features.kanji.mapper;

import com.japan_go_be.features.kanji.dto.response.KanjiPageResponse;
import com.japan_go_be.features.kanji.dto.response.KanjiResponse;
import com.japan_go_be.features.kanji.entity.*;
import com.japan_go_be.features.kanji.helper.KanjiHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KanjiDtoMapper {
    private final ModelMapper modelMapper;
    private final KanjiHelper kanjiHelper;

    public KanjiResponse kanjiEntityToKanjiResponse(KanjiEntity kanjiEntity) {
        KanjiResponse kanjiResponse = modelMapper.map(kanjiEntity, KanjiResponse.class);
        SinoVietnameseEntity mainSinoVietnamese = kanjiEntity.getMainSinoVietnamese();
        if (mainSinoVietnamese != null) kanjiResponse.setMainSinoVietnamese(mainSinoVietnamese.getReadingText());
        
        kanjiResponse.setOnyomiList(kanjiEntity.getOnyomiList().stream().map(OnyomiEntity::getReadingText).toList());
        kanjiResponse.setKunyomiList(kanjiEntity.getKunyomiList().stream().map(KunyomiEntity::getReadingText).toList());
        kanjiResponse.setSvg(kanjiHelper.getSvgOfKanjiCharacter(kanjiEntity.getKanjiCharacter()));
        return kanjiResponse;
    }

    public KanjiPageResponse kanjiPageEntityToKanjiPageResponse(KanjiPageEntity kanjiPageEntity) {
        KanjiPageResponse kanjiPageResponse = modelMapper.map(kanjiPageEntity, KanjiPageResponse.class);
        kanjiPageResponse.setMainKanji(kanjiEntityToKanjiResponse(kanjiPageEntity.getMainKanji()));
        return kanjiPageResponse;
    }
}
