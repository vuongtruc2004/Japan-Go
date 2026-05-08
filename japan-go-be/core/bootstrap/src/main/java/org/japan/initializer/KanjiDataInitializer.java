package org.japan.initializer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.japan.constants.ContentTypes;
import org.japan.exception.FileNotValidException;
import org.japan.i18n.I18nService;
import org.japan.message.FileMessage;
import org.japan.persistence.repository.kanji.KanjiRepository;
import org.japan.service.kanji.KanjiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(1)
public class KanjiDataInitializer implements CommandLineRunner {

    private final KanjiRepository kanjiRepository;
    private final I18nService i18nService;
    private final ResourceLoader resourceLoader;
    private final KanjiService kanjiService;

    @Value("${sources.uri}")
    private String sourcesUri;

    @Override
    public void run(String... args) {
        if (kanjiRepository.existsBy()) {
            log.info("Kanji existed!");
        } else {
            try (InputStream kanjidicInputstream =
                         resourceLoader.getResource(sourcesUri + "/kanjidic/kanjidic2.xml").getInputStream();
                 InputStream kanjiJlptInputstream =
                         resourceLoader.getResource(sourcesUri + "/kanjidic/kanji_jlpt.json").getInputStream()
            ) {
                MultipartFile kanjidicFile = new MockMultipartFile(
                        "kanjidicFile",
                        "kanjidic2.xml",
                        ContentTypes.APPLICATION_XML,
                        kanjidicInputstream
                );

                MultipartFile kanjiJlptFile = new MockMultipartFile(
                        "kanjiJlptFile",
                        "kanji_jlpt.json",
                        ContentTypes.APPLICATION_JSON,
                        kanjiJlptInputstream
                );

                log.info("Kanji importing...");
                kanjiService.importKanjiFromKanjidic(kanjidicFile, kanjiJlptFile);
                log.info("Kanji imported!");

            } catch (Exception e) {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
                );
            }
        }
    }
}

