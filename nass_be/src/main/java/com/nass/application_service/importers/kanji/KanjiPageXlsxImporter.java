package com.nass.application_service.importers.kanji;

import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.exceptions.NotFoundException;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.contract.constants.messages.KanjiMessage;
import com.nass.contract.constants.messages.common.FileMessage;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.entities.kanji.KanjiPageEntity;
import com.nass.infrastructure.entities.vocabulary.VocabularyEntity;
import com.nass.infrastructure.repositories.kanji.KanjiPageRepository;
import com.nass.infrastructure.repositories.kanji.KanjiRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KanjiPageXlsxImporter {

    private final I18nService i18nService;
    private final KanjiPageRepository kanjiPageRepository;
    private final KanjiRepository kanjiRepository;

    public List<KanjiPageEntity> importKanjiPage(InputStream inputStream) {
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();

            Map<String, KanjiPageEntity> map = new LinkedHashMap<>();
            for (int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
                Row row = sheet.getRow(rowNum);
                String mainKanjiCharacter = row.getCell(0).getStringCellValue().trim();
                String japanese = row.getCell(1).getStringCellValue().trim();
                String reading = row.getCell(3).getStringCellValue().trim();
                String vietnamese = row.getCell(4).getStringCellValue().trim();
                String note = row.getCell(5).getStringCellValue().trim();

                if (mainKanjiCharacter.isEmpty()
                        || japanese.isEmpty()
                        || reading.isEmpty()
                        || vietnamese.isEmpty()) {
                    throw new FileNotValidException(
                            i18nService.translation(FileMessage.FILE_ERROR_AT_LINE, rowNum),
                            i18nService.translation(FileMessage.FILE_ERROR_AT_LINE, rowNum)
                    );
                }
                VocabularyEntity vocabulary = VocabularyEntity.builder()
                        .japanese(japanese)
                        .reading(reading)
                        .vietnamese(vietnamese)
                        .note(note)
                        .build();

                if (map.containsKey(mainKanjiCharacter)) {
                    map.get(mainKanjiCharacter).getVocabularies().add(vocabulary);
                } else {
                    KanjiPageEntity kanjiPageEntity = KanjiPageEntity.builder().build();
                    kanjiPageEntity.getVocabularies().add(vocabulary);
                    map.put(mainKanjiCharacter, kanjiPageEntity);
                }
            }
            for (Map.Entry<String, KanjiPageEntity> entry : map.entrySet()) {
                String mainKanjiCharacter = entry.getKey();
                KanjiEntity mainKanji = kanjiRepository.findByKanjiCharacter(mainKanjiCharacter)
                        .orElseThrow(() -> new NotFoundException(
                                i18nService.translation(KanjiMessage.KANJI_CHARACTER_NOT_FOUND, mainKanjiCharacter),
                                i18nService.translation(KanjiMessage.KANJI_CHARACTER_NOT_FOUND, mainKanjiCharacter)
                        ));
                entry.getValue().setMainKanji(mainKanji);
            }
            return kanjiPageRepository.saveAll(map.values());
        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }
}
