package org.japan.initializer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.japan.constants.ContentTypes;
import org.japan.exception.FileNotValidException;
import org.japan.i18n.I18nService;
import org.japan.message.FileMessage;
import org.japan.persistence.repository.kanji.SinoVietnameseRepository;
import org.japan.service.kanji.SinoVietnameseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(2)
public class MainSinoVietnameseInitializer implements CommandLineRunner {
    private final SinoVietnameseRepository sinoVietnameseRepository;
    private final ResourceLoader resourceLoader;
    private final I18nService i18nService;
    private final SinoVietnameseService sinoVietnameseService;

    @Value("${sources.uri}")
    private String sourcesUri;

    @Override
    public void run(String... args) {
        if (sinoVietnameseRepository.existsBy()) {
            log.info("Sino Vietnamese existed!");
        } else {
            try (InputStream sinoVietnamese1Inputstream =
                         resourceLoader.getResource(sourcesUri + "/sino/sino_vietnamese_1.json").getInputStream();
                 InputStream sinoVietnamese2Inputstream =
                         resourceLoader.getResource(sourcesUri + "/sino/sino_vietnamese_2.json").getInputStream();
                 InputStream mainSinoVietnameseInputstream =
                         resourceLoader.getResource(sourcesUri + "/sino/main_sino_vietnamese.xlsx").getInputStream()
            ) {
                MultipartFile sinoVietnamese1File = new MockMultipartFile(
                        "sinoVietnamese1File",
                        "sino_vietnamese_1.json",
                        ContentTypes.APPLICATION_JSON,
                        sinoVietnamese1Inputstream
                );

                MultipartFile sinoVietnamese2File = new MockMultipartFile(
                        "sinoVietnamese2File",
                        "sino_vietnamese_2.json",
                        ContentTypes.APPLICATION_JSON,
                        sinoVietnamese2Inputstream
                );

                MultipartFile mainSinoVietnameseFile = new MockMultipartFile(
                        "mainSinoVietnameseFile",
                        "main_sino_vietnamese.xlsx",
                        ContentTypes.APPLICATION_XLSX,
                        mainSinoVietnameseInputstream
                );

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
