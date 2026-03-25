package com.japan_go_be.infrastructure.mappers.lesson;

import com.japan_go_be.infrastructure.entities.lesson.LessonEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface LessonMapper {
    LessonEntity findLessonByGrammarId(@Param("grammarId") Long grammarId);
}
