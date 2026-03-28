package com.japan_go_be.business.dto.mappers.lesson;

import com.japan_go_be.business.dto.responses.lesson.BookResponse;
import com.japan_go_be.infrastructure.entities.lesson.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookDtoMapper {

    BookResponse mapBookEntityToBookResponseSummary(BookEntity bookEntity);
}
