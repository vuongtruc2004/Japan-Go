package org.japan.mapper.lesson;

import org.japan.dto.response.lesson.BookResponse;
import org.japan.entity.lesson.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse mapEntityToResponseDetails(BookEntity bookEntity);
}
