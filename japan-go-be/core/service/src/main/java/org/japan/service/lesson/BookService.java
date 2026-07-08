package org.japan.service.lesson;

import lombok.RequiredArgsConstructor;
import org.japan.dto.response.lesson.BookResponse;
import org.japan.entity.lesson.BookEntity;
import org.japan.mapper.lesson.BookMapper;
import org.japan.persistence.repository.lesson.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookResponse> getAllBooks() {
        List<BookEntity> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::mapEntityToResponseDetails)
                .toList();
    }
}
