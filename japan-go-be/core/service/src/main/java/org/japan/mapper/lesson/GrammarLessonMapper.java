package org.japan.mapper.lesson;

import org.japan.dto.response.lesson.GrammarLessonResponse;
import org.japan.entity.lesson.GrammarLessonEntity;
import org.japan.mapper.grammar.GrammarMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {GrammarMapper.class}
)
public interface GrammarLessonMapper {
    @Mapping(target = "grammars", source = "grammars", qualifiedByName = "details")
    GrammarLessonResponse mapEntityToResponseDetails(GrammarLessonEntity entity);
}
