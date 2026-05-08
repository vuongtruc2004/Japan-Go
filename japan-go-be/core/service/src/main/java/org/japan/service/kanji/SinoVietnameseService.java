package org.japan.service.kanji;

import lombok.RequiredArgsConstructor;
import org.japan.dto.mapper.KanjiDtoMapper;
import org.japan.dto.request.kanji.GetSinoVietnameseRequest;
import org.japan.entry.SinoVietnameseEntry;
import org.japan.exception.FileNotValidException;
import org.japan.helper.kanji.SinoVietnameseHelper;
import org.japan.i18n.I18nService;
import org.japan.importer.kanji.MainSinoVietnameseXlsxImport;
import org.japan.importer.kanji.SinoVietnameseJsonImporter;
import org.japan.message.FileMessage;
import org.japan.persistence.repository.kanji.KanjiRepository;
import org.japan.validator.FileValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SinoVietnameseService {
    private final KanjiDtoMapper kanjiDTOMapper;
    private final FileValidator fileValidator;
    private final I18nService i18nService;
    private final SinoVietnameseHelper sinoVietnameseHelper;
    private final SinoVietnameseJsonImporter sinoVietnameseJsonImporter;
    private final MainSinoVietnameseXlsxImport mainSinoVietnameseXlsxImport;
    private final KanjiRepository kanjiRepository;

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
    public void importMainSinoVietnamese(MultipartFile mainSinoVietnameseFile) {
        if (!fileValidator.isExcelFile(mainSinoVietnameseFile)) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL),
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL)
            );
        }
        try (InputStream mainSinoVietnameseInputstream = mainSinoVietnameseFile.getInputStream()) {
            Map<String, String> mainSinoVietnameseMap =
                    mainSinoVietnameseXlsxImport.importMainSinoVietnamese(mainSinoVietnameseInputstream);

            mainSinoVietnameseXlsxImport.updateKanjiWithMainSinoVietnamese(mainSinoVietnameseMap);
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
    public void importSinoVietnamese(List<MultipartFile> sinoVietnameseFiles) {
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
        Map<String, List<SinoVietnameseEntry>> sinoVietnameseEntryMap = new HashMap<>();
        for (InputStream sinoVietnameseInputstream : sinoVietnameseInputStreamList) {
            sinoVietnameseJsonImporter.importSinoVietnamese(sinoVietnameseInputstream, sinoVietnameseEntryMap);
        }
        sinoVietnameseJsonImporter.updateKanjiWithSinoVietnamese(sinoVietnameseEntryMap);
    }
}
