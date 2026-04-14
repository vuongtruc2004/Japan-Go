package com.japan_go_be.business.dto.mappers.kanji;

import com.japan_go_be.business.dto.responses.kanji.KanjiResponse;
import com.japan_go_be.infrastructure.entities.kanji.KanjiEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KanjiMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "kanjiCharacter", target = "kanjiCharacter")
    @Mapping(source = "id", target = "id")
    KanjiResponse mapKanjiEntityToKanjiResponse(KanjiEntity kanjiEntity);
}
