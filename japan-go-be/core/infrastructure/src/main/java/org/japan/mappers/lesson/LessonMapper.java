package org.japan.mappers.lesson;

import org.apache.ibatis.annotations.Mapper;
import org.japan.entity.lesson.LessonEntity;
import org.springframework.data.repository.query.Param;

@Mapper
public interface LessonMapper {
    LessonEntity findLessonByGrammarId(@Param("grammarId") Long grammarId);
}
