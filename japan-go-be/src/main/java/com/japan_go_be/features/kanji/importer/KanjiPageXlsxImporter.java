package com.japan_go_be.features.kanji.importer;

import com.japan_go_be.common.constant.message.FileMessage;
import com.japan_go_be.common.exception.FileNotValidException;
import com.japan_go_be.common.exception.NotFoundException;
import com.japan_go_be.common.i18n.I18nService;
import com.japan_go_be.features.kanji.constant.messages.KanjiMessage;
import com.japan_go_be.features.kanji.entity.KanjiEntity;
import com.japan_go_be.features.kanji.entity.KanjiPageEntity;
import com.japan_go_be.features.kanji.repository.KanjiPageRepository;
import com.japan_go_be.features.kanji.repository.KanjiRepository;
import com.japan_go_be.features.vocabulary.entity.VocabularyEntity;
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
            for (int rowNum = 0; rowNum <= lastRowNum; rowNum++) {
                Row row = sheet.getRow(rowNum);
                String mainKanjiCharacter = row.getCell(0).getStringCellValue().trim();
                String japanese = row.getCell(1).getStringCellValue().trim();
                String reading = row.getCell(3).getStringCellValue().trim();
                String meaning = row.getCell(4).getStringCellValue().trim();
                String note = row.getCell(5).getStringCellValue().trim();

                if (mainKanjiCharacter.isEmpty()
                        || japanese.isEmpty()
                        || reading.isEmpty()
                        || meaning.isEmpty()) {
                    throw new FileNotValidException(
                            i18nService.translation(FileMessage.FILE_ERROR_AT_LINE, rowNum),
                            i18nService.translation(FileMessage.FILE_ERROR_AT_LINE, rowNum)
                    );
                }
                VocabularyEntity vocabulary = VocabularyEntity.builder()
                        .japanese(japanese)
                        .reading(reading)
                        .meaning(meaning)
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
