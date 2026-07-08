package org.japan.mapper.lesson;

import org.japan.dto.response.lesson.KanjiLessonResponse;
import org.japan.entity.lesson.KanjiLessonEntity;
import org.japan.mapper.kanji.KanjiPageMapper;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {KanjiPageMapper.class}
)
public interface KanjiLessonMapper {
    KanjiLessonResponse mapEntityToResponseDetails(KanjiLessonEntity entity);
}
