package org.japan.importers.kanji;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.japan.entity.kanji.KanjiEntity;
import org.japan.entity.kanji.SinoVietnameseEntity;
import org.japan.exceptions.FileNotValidException;
import org.japan.exceptions.NotFoundException;
import org.japan.i18n.I18nService;
import org.japan.mappers.kanji.KanjiMapper;
import org.japan.message.FileMessage;
import org.japan.message.kanji.SinoVietnameseMessage;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MainSinoVietnameseXlsxImport {
    private final I18nService i18nService;
    private final KanjiMapper kanjiMapper;

    public Map<String, String> importMainSinoVietnamese(InputStream mainSinoVietnameseInputstream) {
        try (Workbook workbook = WorkbookFactory.create(mainSinoVietnameseInputstream)) {
            Map<String, String> mainSinoVietnameseMap = new HashMap<>();

            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();

            for (int rowNum = 0; rowNum <= lastRowNum; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null) {
                    String kanjiCharacter = row.getCell(1).getStringCellValue().trim();
                    if (kanjiCharacter.isBlank()) {
                        throw new FileNotValidException(
                                i18nService.translation(FileMessage.FILE_ERROR_AT_LINE, row),
                                i18nService.translation(FileMessage.FILE_ERROR_AT_LINE, row)
                        );
                    }
                    String mainSinoVietnamese = row.getCell(2).getStringCellValue().trim().toUpperCase();
                    mainSinoVietnameseMap.putIfAbsent(kanjiCharacter, mainSinoVietnamese);
                }
            }
            return mainSinoVietnameseMap;
        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }

    public List<KanjiEntity> updateKanjiWithMainSinoVietnamese(Map<String, String> mainSinoVietnameseMap) {
        List<KanjiEntity> kanjiEntities = kanjiMapper.findAllKanjiWithSinoVietnameseByKanjiCharacterIn(mainSinoVietnameseMap.keySet());

        for (KanjiEntity kanjiEntity : kanjiEntities) {
            String kanjiCharacter = kanjiEntity.getKanjiCharacter();
            String readingText = mainSinoVietnameseMap.get(kanjiCharacter);

            SinoVietnameseEntity mainSinoVietnameseEntity = kanjiEntity.getSinoVietnameseList()
                    .stream()
                    .filter(sinoVietnameseEntity -> sinoVietnameseEntity.getReadingText().equalsIgnoreCase(readingText))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException(
                            i18nService.translation(SinoVietnameseMessage.SINO_VIETNAMESE_IN_KANJI_NOT_FOUND, readingText, kanjiCharacter),
                            i18nService.translation(SinoVietnameseMessage.SINO_VIETNAMESE_IN_KANJI_NOT_FOUND, readingText, kanjiCharacter)
                    ));
            kanjiEntity.setMainSinoVietnamese(mainSinoVietnameseEntity);
        }
        return kanjiEntities;
    }

}
