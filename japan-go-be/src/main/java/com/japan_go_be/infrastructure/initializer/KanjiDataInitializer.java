package com.japan_go_be.infrastructure.initializer;

import com.japan_go_be.business.exception.FileNotValidException;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.business.services.kanji.KanjiService;
import com.japan_go_be.business.services.kanji.SinoVietnameseService;
import com.japan_go_be.contract.constants.ContentType;
import com.japan_go_be.contract.constants.message.FileMessage;
import com.japan_go_be.infrastructure.repositories.kanji.KanjiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(1)
public class KanjiDataInitializer implements CommandLineRunner {

    private final KanjiRepository kanjiRepository;
    private final I18nService i18nService;
    private final ResourceLoader resourceLoader;
    private final KanjiService kanjiService;
    private final SinoVietnameseService sinoVietnameseService;

    @Value("${sources.uri}")
    private String sourcesUri;

    @Override
    @Transactional
    public void run(String... args) {
        if (!kanjiRepository.existsBy()) {
            log.info("Kanji existed!");
        } else {
            try (InputStream kanjidicInputstream =
                         resourceLoader.getResource(sourcesUri + "/kanjidic/kanjidic2.xml").getInputStream();
                 InputStream kanjiJlptInputstream =
                         resourceLoader.getResource(sourcesUri + "/kanjidic/kanji_jlpt.json").getInputStream();
                 InputStream sinoVietnamese1Inputstream =
                         resourceLoader.getResource(sourcesUri + "/sino/sino_vietnamese_1.json").getInputStream();
                 InputStream sinoVietnamese2Inputstream =
                         resourceLoader.getResource(sourcesUri + "/sino/sino_vietnamese_2.json").getInputStream();
                 InputStream mainSinoVietnameseInputstream =
                         resourceLoader.getResource(sourcesUri + "/sino/main_sino_vietnamese.xlsx").getInputStream()
            ) {
                MultipartFile kanjidicFile = new MockMultipartFile(
                        "kanjidicFile",
                        "kanjidic2.xml",
                        ContentType.APPLICATION_XML,
                        kanjidicInputstream
                );

                MultipartFile kanjiJlptFile = new MockMultipartFile(
                        "kanjiJlptFile",
                        "kanji_jlpt.json",
                        ContentType.APPLICATION_JSON,
                        kanjiJlptInputstream
                );

                MultipartFile sinoVietnamese1File = new MockMultipartFile(
                        "sinoVietnamese1File",
                        "sino_vietnamese_1.json",
                        ContentType.APPLICATION_JSON,
                        sinoVietnamese1Inputstream
                );

                MultipartFile sinoVietnamese2File = new MockMultipartFile(
                        "sinoVietnamese2File",
                        "sino_vietnamese_2.json",
                        ContentType.APPLICATION_JSON,
                        sinoVietnamese2Inputstream
                );

                MultipartFile mainSinoVietnameseFile = new MockMultipartFile(
                        "mainSinoVietnameseFile",
                        "main_sino_vietnamese.xlsx",
                        ContentType.APPLICATION_XLSX,
                        mainSinoVietnameseInputstream
                );

                log.info("Kanji importing...");
                kanjiService.importKanjiFromKanjidic(kanjidicFile, kanjiJlptFile);
                log.info("Kanji imported!");

                log.info("Sino Vietnamese importing...");
                sinoVietnameseService.importSinoVietnamese(List.of(sinoVietnamese1File, sinoVietnamese2File));
                log.info("Sino Vietnamese imported!");

                log.info("Main SinoVietnamese importing...");
                sinoVietnameseService.importMainSinoVietnamese(mainSinoVietnameseFile);
                log.info("Main SinoVietnamese imported!");

            } catch (Exception e) {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
                );
            }
        }
    }
}

