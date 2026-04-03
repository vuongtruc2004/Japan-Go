package com.japan_go_be.business.services.common;

import com.japan_go_be.business.exceptions.FileNotValidException;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.business.validators.FileValidator;
import com.japan_go_be.contract.constants.message.FileMessage;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PowerPointService {
    private final FileValidator fileValidator;
    private final I18nService i18nService;

    public byte[] mergePowerPointList(List<MultipartFile> files) {
        try (XMLSlideShow merge = new XMLSlideShow();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            for (MultipartFile file : files) {
                if (!fileValidator.isPowerPointFile(file)) {
                    throw new FileNotValidException(
                            i18nService.translation(FileMessage.FILE_NOT_POWER_POINT),
                            i18nService.translation(FileMessage.FILE_NOT_POWER_POINT)
                    );
                }

                try (InputStream inputStream = file.getInputStream();
                     XMLSlideShow currentShow = new XMLSlideShow(inputStream)) {
                    for (XSLFSlide slide : currentShow.getSlides()) {
                        XSLFSlide newSlide = merge.createSlide();
                        newSlide.importContent(slide);
                    }
                }
            }

            merge.write(outputStream);
            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }
}
