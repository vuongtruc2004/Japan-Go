package com.japan_go_be.features.sentence.service;

import com.japan_go_be.common.constant.message.FileMessage;
import com.japan_go_be.common.exception.FileNotValidException;
import com.japan_go_be.common.i18n.I18nService;
import com.japan_go_be.features.grammar.helper.GrammarHelper;
import com.japan_go_be.features.sentence.entity.SentenceEntity;
import com.japan_go_be.features.sentence.repository.SentenceRepository;
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
