package com.japan_go_be.features.kanji.initializer;

import com.japan_go_be.common.constant.FileMessage;
import com.japan_go_be.common.exception.FileNotValidException;
import com.japan_go_be.common.i18n.I18nService;
import com.japan_go_be.features.kanji.helper.KanjiHelper;
import com.japan_go_be.features.kanji.helper.SinoVietnameseHelper;
import com.japan_go_be.features.kanji.repository.KanjiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(1)
public class KanjiDataInitializer implements CommandLineRunner {

    private final KanjiRepository kanjiRepository;
    private final I18nService i18nService;
    private final KanjiHelper kanjiServiceHelper;
    private final SinoVietnameseHelper sinoVietnameseHelper;
    private final ResourceLoader resourceLoader;

    @Value("${sources.uri}")
    private String sourcesUri;

    @Override
    @Transactional
    public void run(String... args) {
        if (kanjiRepository.existsBy()) {
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
                log.info("Kanji importing...");
                kanjiServiceHelper.importKanjiFromKanjidic(kanjidicInputstream, kanjiJlptInputstream);
                log.info("Kanji imported!");

                log.info("Sino Vietnamese importing...");
                sinoVietnameseHelper.importSinoVietnamese(List.of(sinoVietnamese1Inputstream, sinoVietnamese2Inputstream));
                log.info("Sino Vietnamese imported!");

                log.info("Main SinoVietnamese importing...");
                sinoVietnameseHelper.importMainSinoVietnamese(mainSinoVietnameseInputstream);
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

