package org.japan.dto.mapper.kanji;

import org.japan.dto.response.kanji.KanjiResponse;
import org.japan.entity.kanji.KanjiEntity;
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
