package com.japan_go_be.infrastructure.mappers.grammar;

import com.japan_go_be.infrastructure.entities.grammar.SentenceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SentenceMapper {
    List<SentenceEntity> findAllByGrammarLessonId(@Param("grammarLessonId") Long grammarLessonId);
}
