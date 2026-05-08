package org.japan.importer.kanji;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.japan.entity.kanji.KanjiEntity;
import org.japan.entity.kanji.SinoVietnameseEntity;
import org.japan.exception.FileNotValidException;
import org.japan.exception.NotFoundException;
import org.japan.i18n.I18nService;
import org.japan.message.FileMessage;
import org.japan.message.kanji.SinoVietnameseMessage;
import org.japan.persistence.mybatis.kanji.KanjiMapper;
import org.japan.persistence.mybatis.kanji.param.UpdateMainSinoVietnameseParam;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainSinoVietnameseXlsxImport {
    private final I18nService i18nService;
    private final KanjiMapper kanjiMapper;

    public Map<String, String> importMainSinoVietnamese(InputStream mainSinoVietnameseInputstream) {
        try (Workbook workbook = WorkbookFactory.create(mainSinoVietnameseInputstream)) {
            Map<String, String> mainSinoVietnameseMap = new HashMap<>();

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                int lastRowNum = sheet.getLastRowNum();

                for (int rowNum = 0; rowNum <= lastRowNum; rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (row != null) {
                        Cell mainSinoVietnameseCell = row.getCell(2);
                        if (mainSinoVietnameseCell != null) {
                            String kanjiCharacter = row.getCell(1).getStringCellValue().trim();
                            if (kanjiCharacter.isBlank()) {
                                throw new FileNotValidException(
                                        i18nService.translation(FileMessage.FILE_ERROR_AT_LINE, row),
                                        i18nService.translation(FileMessage.FILE_ERROR_AT_LINE, row)
                                );
                            }
                            String mainSinoVietnamese = mainSinoVietnameseCell.getStringCellValue().trim().toUpperCase();
                            mainSinoVietnameseMap.putIfAbsent(kanjiCharacter, mainSinoVietnamese);
                        }
                    }
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
        List<KanjiEntity> kanjiEntities = kanjiMapper
                .findAllKanjiWithSinoVietnameseByKanjiCharacterIn(mainSinoVietnameseMap.keySet());

        log.info("MainSinoVietnamese - kanjiEntities size: {}", kanjiEntities.size());

        List<UpdateMainSinoVietnameseParam> params = new ArrayList<>();

        for (KanjiEntity kanjiEntity : kanjiEntities) {
            String kanjiCharacter = kanjiEntity.getKanjiCharacter();
            String readingText = mainSinoVietnameseMap.get(kanjiCharacter);

            SinoVietnameseEntity mainSinoVietnameseEntity = kanjiEntity
                    .getSinoVietnameseList()
                    .stream()
                    .filter(sinoVietnameseEntity -> sinoVietnameseEntity.getReadingText().equalsIgnoreCase(readingText))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException(
                            i18nService.translation(SinoVietnameseMessage.SINO_VIETNAMESE_IN_KANJI_NOT_FOUND, readingText, kanjiCharacter),
                            i18nService.translation(SinoVietnameseMessage.SINO_VIETNAMESE_IN_KANJI_NOT_FOUND, readingText, kanjiCharacter)
                    ));

            kanjiEntity.setMainSinoVietnamese(mainSinoVietnameseEntity);

            params.add(new UpdateMainSinoVietnameseParam(
                    kanjiEntity.getId(),
                    mainSinoVietnameseEntity.getId())
            );
        }

        updateMainSinoVietnameseInChunks(params);
        return kanjiEntities;
    }

    private void updateMainSinoVietnameseInChunks(List<UpdateMainSinoVietnameseParam> params) {
        for (int start = 0; start < params.size(); start += 1000) {
            int end = Math.min(start + 1000, params.size());
            List<UpdateMainSinoVietnameseParam> chunk = params.subList(start, end);
            kanjiMapper.updateMainSinoVietnamese(chunk);
        }
    }

}
