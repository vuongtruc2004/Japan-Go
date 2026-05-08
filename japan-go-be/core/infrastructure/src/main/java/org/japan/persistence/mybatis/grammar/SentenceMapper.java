package org.japan.persistence.mybatis.grammar;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.japan.entity.grammar.SentenceEntity;

import java.util.List;

@Mapper
public interface SentenceMapper {
    List<SentenceEntity> findAllByGrammarLessonId(@Param("grammarLessonId") Long grammarLessonId);
}
