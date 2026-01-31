package com.nass.application_service.importers.lesson;

import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.exceptions.NotFoundException;
import com.nass.application_service.exceptions.lesson.GrammarLessonException;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.contract.constants.messages.common.FileMessage;
import com.nass.contract.constants.messages.lesson.GrammarLessonMessage;
import com.nass.contract.enums.lesson.LessonTypeEnum;
import com.nass.infrastructure.entities.lesson.GrammarLessonEntity;
import com.nass.infrastructure.entities.lesson.LessonEntity;
import com.nass.infrastructure.repositories.lesson.GrammarLessonRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonXlsxImporter {
    private final I18nService i18nService;
    private final GrammarLessonRepository grammarLessonRepository;

    /**
     * @param file excel file to get the lesson name and grammar lesson id
     * @return list of lesson entity and then save it to the database
     */
    public List<LessonEntity> importGrammarLessonsFromExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            List<LessonEntity> lessonEntities = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);

            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                Integer grammarLessonId = (int) row.getCell(1).getNumericCellValue();
                GrammarLessonEntity grammarLesson = grammarLessonRepository
                        .findById(grammarLessonId)
                        .orElseThrow(() -> new NotFoundException(
                                i18nService.translation(GrammarLessonMessage.GRAMMAR_LESSON_NOT_FOUND, grammarLessonId),
                                i18nService.translation(GrammarLessonMessage.GRAMMAR_LESSON_NOT_FOUND, grammarLessonId)
                        ));
                if (grammarLesson.getLesson() != null) {
                    throw new GrammarLessonException(
                            i18nService.translation(GrammarLessonMessage.GRAMMAR_LESSON_LESSON_EXISTED, grammarLessonId),
                            i18nService.translation(GrammarLessonMessage.GRAMMAR_LESSON_LESSON_EXISTED, grammarLessonId)
                    );
                }
                LessonEntity lesson = LessonEntity.builder()
                        .lessonName(row.getCell(0).getStringCellValue())
                        .grammarLesson(grammarLesson)
                        .lessonType(LessonTypeEnum.GRAMMAR)
                        .build();
                grammarLesson.setLesson(lesson);
                lessonEntities.add(lesson);
            }

            return lessonEntities;
        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }
}
