package com.nass.application_service.dtos.mappers;

import com.nass.application_service.dtos.responses.kanji.KanjiResponse;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.entities.kanji.KanjiMeaningEntity;
import com.nass.infrastructure.entities.kanji.KunyomiEntity;
import com.nass.infrastructure.entities.kanji.OnyomiEntity;
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
        kanjiResponse.setOnyomiList(kanjiEntity.getOnyomiList().stream().map(OnyomiEntity::getReadingText).toList());
        kanjiResponse.setKunyomiList(kanjiEntity.getKunyomiList().stream().map(KunyomiEntity::getReadingText).toList());
        kanjiResponse.setKanjiMeaningList(kanjiEntity.getKanjiMeaningList().stream().map(KanjiMeaningEntity::getReadingText).toList());
        return kanjiResponse;
    }
}
