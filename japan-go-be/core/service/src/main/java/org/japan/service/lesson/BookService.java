package org.japan.service.lesson;

import lombok.RequiredArgsConstructor;
import org.japan.dto.mapper.lesson.BookDtoMapper;
import org.japan.dto.response.lesson.BookResponse;
import org.japan.entity.lesson.BookEntity;
import org.japan.persistence.repository.lesson.BookRepository;
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
