package com.nass.application_service.importers;

import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.exceptions.NotFoundException;
import com.nass.application_service.validators.FileValidator;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.entities.kanji.SinoVietnameseEntity;
import com.nass.infrastructure.repositories.KanjiRepository;
import com.nass.infrastructure.repositories.SinoVietnameseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MainSinoVietnameseXlsxImport {
    private final FileValidator fileValidator;
    private final KanjiRepository kanjiRepository;
    private final SinoVietnameseRepository sinoVietnameseRepository;

    public Map<String, String> importMainSinoVietnamese(MultipartFile mainSinoVietnameseFile) {
        if (!fileValidator.isExcelFile(mainSinoVietnameseFile)) {
            throw new FileNotValidException("File lo", "");
        }
        try (InputStream inputStream = mainSinoVietnameseFile.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)
        ) {
            Map<String, String> mainSinoVietnameseMap = new HashMap<>();

            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();

            for (int rowNum = 0; rowNum <= lastRowNum; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null) {
                    String kanjiCharacter = row.getCell(1).getStringCellValue().trim();
                    String mainSinoVietnamese = "";
                    for (int colNum = 2; colNum < 7; colNum++) {
                        Cell cell = row.getCell(colNum);
                        if (cell != null) {
                            mainSinoVietnamese = cell.getStringCellValue().trim().toUpperCase();
                            break;
                        }
                    }
                    if (kanjiCharacter.isBlank()) {
                        throw new FileNotValidException("File error at row: " + rowNum);
                    }
                    mainSinoVietnameseMap.putIfAbsent(kanjiCharacter, mainSinoVietnamese);
                }
            }
            return mainSinoVietnameseMap;
        } catch (Exception e) {
            throw new FileNotValidException(e.getMessage());
        }
    }

    public List<KanjiEntity> updateKanjiWithMainSinoVietnamese(Map<String, String> mainSinoVietnameseMap) {
        List<KanjiEntity> kanjiEntities = kanjiRepository.findAll();

        for (KanjiEntity kanjiEntity : kanjiEntities) {
            String kanjiCharacter = kanjiEntity.getKanjiCharacter();

            if (mainSinoVietnameseMap.containsKey(kanjiCharacter)) {
                String readingText = mainSinoVietnameseMap.get(kanjiCharacter);

                SinoVietnameseEntity mainSinoVietnameseEntity = sinoVietnameseRepository
                        .findByKanji_KanjiCharacterAndReadingText(kanjiCharacter, readingText)
                        .orElseThrow(() -> new NotFoundException(
                                "Main reading text '" + readingText + "' for kanji '" + kanjiCharacter + "' not found!"
                        ));
                kanjiEntity.setMainSinoVietnamese(mainSinoVietnameseEntity);
            } else {
                var list = kanjiEntity.getSinoVietnameseList();
                if (list != null && !list.isEmpty()) {
                    SinoVietnameseEntity mainSinoVietnameseEntity = list.getFirst();
                    kanjiEntity.setMainSinoVietnamese(mainSinoVietnameseEntity);
                }
            }
        }

        return kanjiEntities;
    }

}
