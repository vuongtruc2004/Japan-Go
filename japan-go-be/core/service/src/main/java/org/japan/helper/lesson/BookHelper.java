package org.japan.helper.lesson;

import lombok.RequiredArgsConstructor;
import org.japan.entity.lesson.BookEntity;
import org.japan.exception.NotFoundException;
import org.japan.i18n.I18nService;
import org.japan.message.lesson.BookMessage;
import org.japan.persistence.repository.lesson.BookRepository;
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
