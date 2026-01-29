package com.nass.application_service.importers.auto;

import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.application_service.services.implementations.kanji.KanjiService;
import com.nass.application_service.services.implementations.kanji.SinoVietnameseService;
import com.nass.contract.constants.FilePath;
import com.nass.contract.constants.messages.common.FileMessage;
import com.nass.infrastructure.repositories.KanjiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(1)
public class KanjiAutoImport implements CommandLineRunner {

    private final KanjiRepository kanjiRepository;
    private final I18nService i18nService;
    private final KanjiService kanjiService;
    private final SinoVietnameseService sinoVietnameseService;

    @Override
    @Transactional
    public void run(String... args) {
        if (kanjiRepository.existsBy()) {
            log.info("Kanji existed!");
        } else {
            try (InputStream kanjidicInputstream =
                         new ClassPathResource(FilePath.KANJIDIC_XML).getInputStream();
                 InputStream kanjiJlptInputstream =
                         new ClassPathResource(FilePath.KANJI_JLPT_JSON).getInputStream();
                 InputStream sinoVietnamese1Inputstream =
                         new ClassPathResource(FilePath.SINO_VIETNAMESE_1_JSON).getInputStream();
                 InputStream sinoVietnamese2Inputstream =
                         new ClassPathResource(FilePath.SINO_VIETNAMESE_2_JSON).getInputStream();
                 InputStream mainSinoVietnameseInputstream =
                         new ClassPathResource(FilePath.MAIN_SINO_VIETNAMESE_XLSX).getInputStream()
            ) {
                log.info("Kanji importing...");
                kanjiService.importKanjiFromKanjidicIS(kanjidicInputstream, kanjiJlptInputstream);
                log.info("Kanji imported!");

                log.info("Sino Vietnamese importing...");
                sinoVietnameseService.importSinoVietnameseIS(List.of(sinoVietnamese1Inputstream, sinoVietnamese2Inputstream));
                log.info("Sino Vietnamese imported!");

                log.info("Main SinoVietnamese importing...");
                sinoVietnameseService.importMainSinoVietnameseIS(mainSinoVietnameseInputstream);
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

