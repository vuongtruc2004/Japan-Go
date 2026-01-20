package com.nass.application_service.services;

import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.helpers.GrammarExampleHelper;
import com.nass.application_service.services.interfaces.IGrammarService;
import com.nass.infrastructure.entities.grammar.GrammarEntity;
import com.nass.infrastructure.repositories.GrammarRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GrammarService implements IGrammarService {
    private final GrammarExampleHelper grammarExampleHelper;
    private final GrammarRepository grammarRepository;

    @Override
    public byte[] exportAllGrammarsToExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("grammars");
            List<GrammarEntity> grammars = grammarRepository.findAll();

            int rowNum = 0;
            for (GrammarEntity grammar : grammars) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(grammarExampleHelper.getGrammarStructure(grammar));
                row.createCell(1).setCellValue(grammar.getGrammarMeaning().getVietnameseTranslation());
                row.createCell(2).setCellValue(grammarExampleHelper.getGrammarMeaning(grammar));
                row.createCell(3).setCellValue(grammarExampleHelper.getGrammarExample(grammar));
                row.createCell(4).setCellValue(grammarExampleHelper.getGrammarAdditionalNote(grammar));
                row.createCell(5).setCellValue(grammarExampleHelper.getGrammarLocation(grammar));
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new FileNotValidException(e.getMessage());
        }
    }
}
