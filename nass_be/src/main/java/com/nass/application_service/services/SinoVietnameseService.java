package com.nass.application_service.services;

import com.nass.application_service.dto.entry.SinoVietnameseEntry;
import com.nass.application_service.dto.mapper.KanjiMapper;
import com.nass.application_service.dto.request.GetSinoVnRequest;
import com.nass.application_service.dto.response.KanjiResponse;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.importers.SinoVietnameseJsonImporter;
import com.nass.application_service.services.interfaces.ISinoVietnameseService;
import com.nass.application_service.validators.FileValidator;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.entities.kanji.SinoVietnameseEntity;
import com.nass.infrastructure.repositories.KanjiRepository;
import com.nass.infrastructure.repositories.SinoVietnameseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SinoVietnameseService implements ISinoVietnameseService {
    private final SinoVietnameseJsonImporter sinoVietnameseJsonImporter;
    private final SinoVietnameseRepository sinoVietnameseRepository;
    private final KanjiRepository kanjiRepository;
    private final KanjiMapper kanjiMapper;
    private final FileValidator fileValidator;

    @Override
    public byte[] buildKanjiAndSinoVietnameseExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Sheet sheet = workbook.createSheet("Kanji");
            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("JLPT");
            header.createCell(1).setCellValue("Kanji");
            header.createCell(2).setCellValue("SinoVietnamese");

            int rowIndex = 1;
            for (int jlptLevel = 5; jlptLevel > 0; jlptLevel--) {
                List<KanjiEntity> kanjiList = kanjiRepository.findAllByJlptLevel(jlptLevel);
                for (KanjiEntity kanjiEntity : kanjiList) {
                    Row row = sheet.createRow(rowIndex);
                    row.createCell(0).setCellValue("N" + jlptLevel);
                    row.createCell(1).setCellValue(String.valueOf(kanjiEntity.getKanjiCharacter()));

                    int colIndex = 2;
                    for (SinoVietnameseEntity sinoVietnamese : kanjiEntity.getSinoVietnameseList()) {
//                        row.createCell(colIndex).setCellValue(sinoVietnamese.getSinoVietnamese());
                        colIndex++;
                    }
                    rowIndex++;
                }
            }

            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new FileNotValidException(e.getMessage());
        }
    }

    @Override
    public List<String> getSinoVietnameseOfKanjiList(GetSinoVnRequest getSinoVnRequest) {
        List<String> sinoVnlist = new ArrayList<>();
        String kanjiRaw = getSinoVnRequest.kanjiRaw();
        String divider = getSinoVnRequest.divider();

        String[] kanjiArray = kanjiRaw.split(divider);
        for (String kanji : kanjiArray) {
            String sinoVn = "";
            for (char c : kanji.toCharArray()) {
                if (c == '（' || c == '(') break;
//                sinoVietnameseRepository
//                        .findTopByKanji_KanjiCharacterOrderByPriorityLevelDesc(c)
//                        .ifPresent(sinoVietnameseEntity -> sinoVn.append(sinoVietnameseEntity.getSinoVietnamese()).append(" "));
            }
            sinoVnlist.add(sinoVn.trim());
        }

        return sinoVnlist;
    }

    @Override
    @Transactional
    public List<KanjiResponse> importSinoVietnamese(List<MultipartFile> sinoVietnameseFiles) {
        Map<String, List<SinoVietnameseEntry>> sinoVietnameseEntryMap = new HashMap<>();
        for (MultipartFile sinoVietnameseFile : sinoVietnameseFiles) {
            sinoVietnameseJsonImporter.importSinoVietnamese(sinoVietnameseFile, sinoVietnameseEntryMap);
        }
        List<KanjiEntity> kanjiEntities = sinoVietnameseJsonImporter.updateKanjiWithSinoVietnamese(sinoVietnameseEntryMap);
        return kanjiEntities.stream().map(kanjiMapper::kanjiEntityToKanjiResponse).toList();
    }
}
