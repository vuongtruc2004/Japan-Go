package com.nass.application_service.services.implementations.kanji;

import com.nass.application_service.dtos.mappers.KanjiDtoMapper;
import com.nass.application_service.dtos.requests.kanji.GetSinoVietnameseRequest;
import com.nass.application_service.dtos.responses.kanji.KanjiResponse;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.helpers.kanji.SinoVietnameseServiceHelper;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.application_service.services.interfaces.kanji.ISinoVietnameseService;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.messages.common.FileMessage;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SinoVietnameseService implements ISinoVietnameseService {
    private final KanjiDtoMapper kanjiDTOMapper;
    private final FileValidator fileValidator;
    private final I18nService i18nService;
    private final SinoVietnameseServiceHelper sinoVietnameseServiceHelper;

    /**
     * @param getSinoVietnameseRequest this object contains
     *                                 kanjiArrayRaw: the kanji array but in the string type
     *                                 all of the kanji in this string is seperated by a same divider such as \n, whitespace
     *                                 divider: the divider to split the kanjiArrayRaw to the String[]
     * @return string of sino vietnamese of all kanji and seperated by "\n"
     */
    @Override
    public String getSinoVietnameseOfKanjiList(GetSinoVietnameseRequest getSinoVietnameseRequest) {
        List<String> mainSinoVietnameseList = new ArrayList<>();
        for (String kanji : sinoVietnameseServiceHelper.getKanjiArray(getSinoVietnameseRequest)) {
            String mainSinoVietnamese = sinoVietnameseServiceHelper.getSinoVietnameseOfKanji(kanji);
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
    @Override
    @Transactional
    public List<KanjiResponse> importMainSinoVietnamese(MultipartFile mainSinoVietnameseFile) {
        if (!fileValidator.isExcelFile(mainSinoVietnameseFile)) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL),
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL)
            );
        }
        try (InputStream mainSinoVietnameseInputstream = mainSinoVietnameseFile.getInputStream()) {
            List<KanjiEntity> kanjiEntities = sinoVietnameseServiceHelper.importMainSinoVietnamese(mainSinoVietnameseInputstream);
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
    @Override
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
        List<KanjiEntity> kanjiEntities = sinoVietnameseServiceHelper.importSinoVietnamese(sinoVietnameseInputStreamList);
        return kanjiEntities.stream().map(kanjiDTOMapper::kanjiEntityToKanjiResponse).toList();
    }
}
