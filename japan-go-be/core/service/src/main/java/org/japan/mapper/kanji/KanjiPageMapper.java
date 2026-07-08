package org.japan.mapper.kanji;

import org.japan.dto.response.kanji.KanjiPageResponse;
import org.japan.entity.kanji.KanjiPageEntity;
import org.japan.mapper.vocabulary.VocabularyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {KanjiMapper.class, VocabularyMapper.class}
)
public interface KanjiPageMapper {
    @Mapping(target = "mainKanji", source = "mainKanji", qualifiedByName = "details")
    KanjiPageResponse mapEntityToResponseDetails(KanjiPageEntity entity);
}
