package org.japan.mappers.lesson;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.japan.entity.lesson.BookEntity;

@Mapper
public interface BookMapper {
    BookEntity findBookByGrammarId(@Param("grammarId") Long grammarId);
}
