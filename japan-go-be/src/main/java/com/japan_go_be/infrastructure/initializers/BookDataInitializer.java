package com.japan_go_be.infrastructure.initializers;

import com.japan_go_be.infrastructure.entities.lesson.BookEntity;
import com.japan_go_be.infrastructure.repositories.lesson.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(2)
public class BookDataInitializer implements CommandLineRunner {
    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {
        if (bookRepository.existsBy()) {
            log.info("Book existed!");
        } else {
            log.info("Book creating...");
            List<BookEntity> books = List.of(
                    BookEntity.builder()
                            .vietnameseTitle("Shinkanzen Master N2")
                            .japaneseTitle("新完全マスターN2")
                            .build(),
                    BookEntity.builder()
                            .vietnameseTitle("Dekiru Nihongo Xanh")
                            .japaneseTitle("中級できる日本語")
                            .build(),
                    BookEntity.builder()
                            .vietnameseTitle("Pawaa Doriru N2")
                            .japaneseTitle("パワードリルN2")
                            .build(),
                    BookEntity.builder()
                            .vietnameseTitle("Chokuzen Taisaku N2")
                            .japaneseTitle("直前対策N2")
                            .build()
            );
            bookRepository.saveAll(books);
            log.info("Book created!");
        }
    }
}
