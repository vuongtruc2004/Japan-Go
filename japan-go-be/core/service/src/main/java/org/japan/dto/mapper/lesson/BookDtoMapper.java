package org.japan.dto.mapper.lesson;

import org.japan.dto.response.lesson.BookResponse;
import org.japan.entity.lesson.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookDtoMapper {

    BookResponse mapBookEntityToBookResponseSummary(BookEntity bookEntity);
}
