package com.japan_go_be.business.helpers.lesson;

import com.japan_go_be.business.exceptions.NotFoundException;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.contract.message.lesson.BookMessage;
import com.japan_go_be.infrastructure.entities.lesson.BookEntity;
import com.japan_go_be.infrastructure.repositories.lesson.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookHelper {
    private final BookRepository bookRepository;
    private final I18nService i18nService;

    public BookEntity getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(
                        i18nService.translation(BookMessage.BOOK_NOT_FOUND, bookId),
                        i18nService.translation(BookMessage.BOOK_NOT_FOUND, bookId)
                ));
    }
}
