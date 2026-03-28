package com.japan_go_be.infrastructure.mappers.lesson;

import com.japan_go_be.infrastructure.entities.lesson.BookEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookMapper {
    BookEntity findBookByGrammarId(@Param("grammarId") Long grammarId);
}
