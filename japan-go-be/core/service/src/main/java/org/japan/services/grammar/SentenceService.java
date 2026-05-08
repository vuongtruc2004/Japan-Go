package org.japan.services.grammar;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.japan.entity.grammar.SentenceEntity;
import org.japan.exceptions.FileNotValidException;
import org.japan.helpers.grammar.GrammarHelper;
import org.japan.i18n.I18nService;
import org.japan.message.FileMessage;
import org.japan.repositories.grammar.SentenceRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SentenceService {
    private final SentenceRepository sentenceRepository;
    private final I18nService i18nService;
    private final GrammarHelper grammarHelper;

    public byte[] exportAllGrammarExampleSentencesInFolder(Long folderId) {
        List<SentenceEntity> sentences = sentenceRepository.findAllGrammarExampleSentencesInFolder(folderId);
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("grammars");
            int rowNum = 0;

            for (SentenceEntity sentence : sentences) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(grammarHelper.createFrontOfFlashcard(sentence));
                row.createCell(1).setCellValue(grammarHelper.createBackOfFlashcard(sentence));
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
