package com.nass.application_service.services;

import com.nass.application_service.dto.responses.grammar.LessonResponse;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.helpers.GrammarServiceHelper;
import com.nass.application_service.importers.GrammarMarkdownImporter;
import com.nass.application_service.services.interfaces.IGrammarService;
import com.nass.application_service.validators.FileValidator;
import com.nass.infrastructure.entities.grammar.GrammarEntity;
import com.nass.infrastructure.entities.grammar.LessonEntity;
import com.nass.infrastructure.repositories.ExampleRepository;
import com.nass.infrastructure.repositories.GrammarRepository;
import com.nass.infrastructure.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GrammarService implements IGrammarService {
    private final GrammarMarkdownImporter grammarMarkdownImporter;
    private final FileValidator fileValidator;
    private final ModelMapper modelMapper;
    private final LessonRepository lessonRepository;
    private final ExampleRepository exampleRepository;
    private final GrammarServiceHelper grammarServiceHelper;
    private final GrammarRepository grammarRepository;

    @Override
    public List<LessonResponse> importLessonsFromNotion(List<MultipartFile> files) {
        List<LessonEntity> lessons = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!fileValidator.isMarkdownFile(file)) {
                throw new FileNotValidException("Markdown file is not valid");
            }

            try (InputStream inputStream = file.getInputStream()) {
                String markdown = new String(inputStream.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8).trim();
                if (markdown.isEmpty()) {
                    throw new FileNotValidException("No markdown content");
                }
                lessons.add(grammarMarkdownImporter.importLessonFromNotion(markdown));
            } catch (Exception e) {
                throw new FileNotValidException(e.getMessage());
            }
        }
        List<LessonEntity> savedLessons = lessonRepository.saveAll(lessons);
        return savedLessons.stream().map(lesson -> modelMapper.map(lesson, LessonResponse.class)).toList();
    }

    @Override
    public byte[] exportLessonsToExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            List<GrammarEntity> grammars = grammarRepository.findAll();
            Sheet sheet = workbook.createSheet("grammars");

            int rowNum = 0;
            for (GrammarEntity grammar : grammars) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(grammarServiceHelper.getGrammarStructure(grammar));
                row.createCell(1).setCellValue(grammar.getGrammarMeaning().getVietnameseTranslation());
                row.createCell(2).setCellValue(grammarServiceHelper.getGrammarMeaning(grammar));
                row.createCell(3).setCellValue(grammarServiceHelper.getGrammarExample(grammar));
                row.createCell(4).setCellValue(grammarServiceHelper.getGrammarAdditionalNote(grammar));
                row.createCell(5).setCellValue(grammarServiceHelper.getGrammarLocation(grammar));
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new FileNotValidException(e.getMessage());
        }
    }
}
