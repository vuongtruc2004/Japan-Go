package com.japan_go_be.features.kanji.service;

import com.japan_go_be.common.constant.FileMessage;
import com.japan_go_be.common.exception.FileNotValidException;
import com.japan_go_be.common.i18n.I18nService;
import com.japan_go_be.common.validator.FileValidator;
import com.japan_go_be.features.kanji.dto.request.GetSinoVietnameseRequest;
import com.japan_go_be.features.kanji.dto.response.KanjiResponse;
import com.japan_go_be.features.kanji.entity.KanjiEntity;
import com.japan_go_be.features.kanji.helper.SinoVietnameseHelper;
import com.japan_go_be.features.kanji.mapper.KanjiDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SinoVietnameseService {
    private final KanjiDtoMapper kanjiDTOMapper;
    private final FileValidator fileValidator;
    private final I18nService i18nService;
    private final SinoVietnameseHelper sinoVietnameseHelper;

    /**
     * @param getSinoVietnameseRequest this object contains
     *                                 kanjiArrayRaw: the kanji array but in the string type
     *                                 all of the kanji in this string is seperated by a same divider such as \n, whitespace
     *                                 divider: the divider to split the kanjiArrayRaw to the String[]
     * @return string of sino vietnamese of all kanji and seperated by "\n"
     */
    public String getSinoVietnameseOfKanjiList(GetSinoVietnameseRequest getSinoVietnameseRequest) {
        List<String> mainSinoVietnameseList = new ArrayList<>();
        for (String kanji : sinoVietnameseHelper.getKanjiArray(getSinoVietnameseRequest)) {
            String mainSinoVietnamese = sinoVietnameseHelper.getSinoVietnameseOfKanji(kanji);
            mainSinoVietnameseList.add(mainSinoVietnamese);
        }
        return String.join("\n", mainSinoVietnameseList);
    }

    /**
     * @param mainSinoVietnameseFile an excel file that contains some kanji character
     *                               and their main sino vietnamese.
     *                               For example: 私 TỰ
     * @return all kanji response that were updated
     */
    @Transactional
    public List<KanjiResponse> importMainSinoVietnamese(MultipartFile mainSinoVietnameseFile) {
        if (!fileValidator.isExcelFile(mainSinoVietnameseFile)) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL),
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL)
            );
        }
        try (InputStream mainSinoVietnameseInputstream = mainSinoVietnameseFile.getInputStream()) {
            List<KanjiEntity> kanjiEntities = sinoVietnameseHelper.importMainSinoVietnamese(mainSinoVietnameseInputstream);
            return kanjiEntities.stream().map(kanjiDTOMapper::kanjiEntityToKanjiResponse).toList();
        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }

    /**
     * @param sinoVietnameseFiles List of JSON file that contains Sino Vietnamese and some other properties
     * @return list of kanji response after updated
     */
    @Transactional
    public List<KanjiResponse> importSinoVietnamese(List<MultipartFile> sinoVietnameseFiles) {
        List<InputStream> sinoVietnameseInputStreamList = new ArrayList<>();
        for (MultipartFile sinoVietnameseFile : sinoVietnameseFiles) {
            if (!fileValidator.isJSONFile(sinoVietnameseFile)) {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_NOT_JSON),
                        i18nService.translation(FileMessage.FILE_NOT_JSON)
                );
            }
            try {
                sinoVietnameseInputStreamList.add(sinoVietnameseFile.getInputStream());
            } catch (Exception e) {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
                );
            }
        }
        List<KanjiEntity> kanjiEntities = sinoVietnameseHelper.importSinoVietnamese(sinoVietnameseInputStreamList);
        return kanjiEntities.stream().map(kanjiDTOMapper::kanjiEntityToKanjiResponse).toList();
    }
}
