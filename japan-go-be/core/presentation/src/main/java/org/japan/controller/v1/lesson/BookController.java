package org.japan.controller.v1.lesson;

import lombok.RequiredArgsConstructor;
import org.japan.annotations.ApiResponseFormat;
import org.japan.dto.response.lesson.BookResponse;
import org.japan.message.lesson.BookMessage;
import org.japan.service.lesson.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @ApiResponseFormat(devMessage = BookMessage.BOOK_GET_ALL_SUCCESS, clientMessage = BookMessage.BOOK_GET_ALL_SUCCESS)
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
