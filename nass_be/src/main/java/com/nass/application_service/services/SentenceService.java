package com.nass.application_service.services;

import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.helpers.GrammarExampleHelper;
import com.nass.application_service.helpers.SentenceHelper;
import com.nass.application_service.services.interfaces.ISentenceService;
import com.nass.infrastructure.entities.grammar.SentenceEntity;
import com.nass.infrastructure.repositories.SentenceRepository;
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
public class SentenceService implements ISentenceService {
    private final SentenceHelper sentenceHelper;
    private final SentenceRepository sentenceRepository;
    private final GrammarExampleHelper grammarExampleHelper;

    @Override
    public byte[] exportAllExampleSentencesToExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("sentences");
            List<SentenceEntity> sentences = sentenceRepository.findAllByExampleNotNull();

            int rowNum = 0;
            for (SentenceEntity sentenceEntity : sentences) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(sentenceHelper.enrichFuriganaToKanjiString(sentenceEntity.getJapanese()));
                row.createCell(1).setCellValue(sentenceEntity.getVietnamese());
                row.createCell(2).setCellValue(grammarExampleHelper.getGrammarMeaning(sentenceEntity.getExample().getGrammar()));
                row.createCell(3).setCellValue(sentenceEntity.getExample().getGrammar().getGrammarMeaning().getVietnameseTranslation());
                row.createCell(4).setCellValue(grammarExampleHelper.getGrammarStructure(sentenceEntity.getExample().getGrammar()));
                row.createCell(5).setCellValue(grammarExampleHelper.getGrammarExample(sentenceEntity.getExample().getGrammar()));
                row.createCell(6).setCellValue(grammarExampleHelper.getGrammarAdditionalNote(sentenceEntity.getExample().getGrammar()));
                row.createCell(7).setCellValue(grammarExampleHelper.getGrammarLocation(sentenceEntity.getExample().getGrammar()));
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new FileNotValidException(e.getMessage());
        }
    }
}
