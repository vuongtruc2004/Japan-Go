package com.nass.application_service.services.implementations.common;

import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.helpers.common.StringHelper;
import com.nass.application_service.helpers.grammar.GrammarHelper;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.application_service.services.interfaces.common.ISentenceService;
import com.nass.contract.constants.messages.common.FileMessage;
import com.nass.infrastructure.entities.common.SentenceEntity;
import com.nass.infrastructure.entities.grammar.GrammarEntity;
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
    private final I18nService i18nService;
    private final SentenceRepository sentenceRepository;
    private final GrammarHelper grammarHelper;
    private final StringHelper stringHelper;

    /**
     * Export to excel file then I can use this file to add flash cards into Anki app
     *
     * @return byte[] and in the controller, it will be parsed into .xlsx file
     */
    @Override
    public byte[] exportAllExampleSentencesToExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("sentences");

            int rowNum = 0;
            List<SentenceEntity> sentences = sentenceRepository.findAllByGrammarExampleNotNull();
            for (SentenceEntity sentenceEntity : sentences) {
                Row row = sheet.createRow(rowNum++);
                GrammarEntity grammar = sentenceEntity.getGrammarExample().getGrammar();

                row.createCell(0).setCellValue(stringHelper.enrichFuriganaToKanjiString(sentenceEntity.getJapaneseHtml()));
                row.createCell(1).setCellValue(sentenceEntity.getVietnameseRaw());
                row.createCell(2).setCellValue(stringHelper.enrichFuriganaToKanjiString(grammar.getGrammarTitle()));
                row.createCell(3).setCellValue(grammarHelper.getGrammarMeaning(grammar));
                row.createCell(4).setCellValue(grammarHelper.getGrammarStructure(grammar));
                row.createCell(5).setCellValue(grammarHelper.getGrammarExample(grammar));
                row.createCell(6).setCellValue(grammarHelper.getGrammarNote(grammar));
                row.createCell(7).setCellValue(grammarHelper.getGrammarLessons(grammar));
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }
}
