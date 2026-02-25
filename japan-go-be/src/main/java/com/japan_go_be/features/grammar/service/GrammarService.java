package com.japan_go_be.features.grammar.service;

import com.japan_go_be.common.constant.message.FileMessage;
import com.japan_go_be.common.exception.FileNotValidException;
import com.japan_go_be.common.i18n.I18nService;
import com.japan_go_be.features.grammar.entity.GrammarEntity;
import com.japan_go_be.features.grammar.helper.GrammarHelper;
import com.japan_go_be.features.grammar.repository.GrammarRepository;
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
public class GrammarService {

    private final GrammarRepository grammarRepository;
    private final I18nService i18nService;
    private final GrammarHelper grammarHelper;

    public byte[] exportAllGrammarsInFolder(Long folderId) {
        List<GrammarEntity> grammars = grammarRepository.findAllByGrammarsInFolder(folderId);
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("grammars");
            int rowNum = 0;
            for (GrammarEntity grammar : grammars) {
                Row row = sheet.createRow(rowNum++);
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
