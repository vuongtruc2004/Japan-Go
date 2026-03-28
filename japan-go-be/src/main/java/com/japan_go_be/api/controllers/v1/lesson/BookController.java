package com.japan_go_be.api.controllers.v1.lesson;

import com.japan_go_be.business.dto.responses.lesson.BookResponse;
import com.japan_go_be.business.services.lesson.BookService;
import com.japan_go_be.contract.annotations.ApiResponseFormat;
import com.japan_go_be.contract.constants.message.lesson.BookMessage;
import lombok.RequiredArgsConstructor;
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
