package com.nass.application_service.dto.mapper;

import com.nass.application_service.dto.response.KanjiResponse;
import com.nass.infrastructure.entities.kanji.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KanjiMapper {
    private final ModelMapper modelMapper;

    public KanjiResponse kanjiEntityToKanjiResponse(KanjiEntity kanjiEntity) {
        KanjiResponse kanjiResponse = modelMapper.map(kanjiEntity, KanjiResponse.class);
        kanjiResponse.setSinoVietnamese(kanjiEntity.getSinoVietnamese().stream().map(SinoVietnameseEntity::getSinoVietnamese).toList());
        kanjiResponse.setOnyomi(kanjiEntity.getOnyomi().stream().map(OnyomiEntity::getOnyomi).toList());
        kanjiResponse.setKunyomi(kanjiEntity.getKunyomi().stream().map(KunyomiEntity::getKunyomi).toList());
        kanjiResponse.setMeanings(kanjiEntity.getMeanings().stream().map(MeaningEntity::getMeaning).toList());
        return kanjiResponse;
    }
}
