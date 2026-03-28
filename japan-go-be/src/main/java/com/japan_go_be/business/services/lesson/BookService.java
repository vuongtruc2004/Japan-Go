package com.japan_go_be.business.services.lesson;

import com.japan_go_be.business.dto.mappers.lesson.BookDtoMapper;
import com.japan_go_be.business.dto.responses.lesson.BookResponse;
import com.japan_go_be.infrastructure.entities.lesson.BookEntity;
import com.japan_go_be.infrastructure.repositories.lesson.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookDtoMapper bookDtoMapper;

    public List<BookResponse> getAllBooks() {
        List<BookEntity> books = bookRepository.findAll();
        return books.stream()
                .map(bookDtoMapper::mapBookEntityToBookResponseSummary)
                .toList();
    }
}
