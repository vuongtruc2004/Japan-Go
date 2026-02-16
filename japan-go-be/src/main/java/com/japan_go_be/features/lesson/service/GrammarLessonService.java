package com.japan_go_be.features.lesson.service;

import com.japan_go_be.common.constant.message.FileMessage;
import com.japan_go_be.common.exception.FileNotValidException;
import com.japan_go_be.common.i18n.I18nService;
import com.japan_go_be.common.validator.FileValidator;
import com.japan_go_be.features.lesson.dto.response.GrammarLessonResponse;
import com.japan_go_be.features.lesson.dto.response.LessonResponse;
import com.japan_go_be.features.lesson.entity.GrammarLessonEntity;
import com.japan_go_be.features.lesson.entity.LessonEntity;
import com.japan_go_be.features.lesson.helper.GrammarLessonMarkdownImporter;
import com.japan_go_be.features.lesson.helper.GrammarLessonXlsxImporter;
import com.japan_go_be.features.lesson.repository.GrammarLessonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GrammarLessonService {
    private final FileValidator fileValidator;
    private final I18nService i18nService;
    private final GrammarLessonMarkdownImporter grammarLessonMarkdownImporter;
    private final GrammarLessonRepository grammarLessonRepository;
    private final ModelMapper modelMapper;
    private final GrammarLessonXlsxImporter grammarLessonXlsxImporter;

    /**
     * @param files list of markdown files by export from the notion (I use for noting grammars)
     * @return list of grammar after inserted to the database
     */
    @Transactional
    public List<GrammarLessonResponse> importGrammarLessonsFromNotion(List<MultipartFile> files) {
        List<GrammarLessonEntity> grammarLessonEntities = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!fileValidator.isMarkdownFile(file)) {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_NOT_MARKDOWN),
                        i18nService.translation(FileMessage.FILE_NOT_MARKDOWN)
                );
            }
            try (InputStream inputStream = file.getInputStream()) {
                String markdown = new String(inputStream.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8).trim();
                if (markdown.isBlank()) {
                    throw new FileNotValidException(
                            i18nService.translation(FileMessage.FILE_EMPTY),
                            i18nService.translation(FileMessage.FILE_EMPTY)
                    );
                }
                grammarLessonEntities.add(grammarLessonMarkdownImporter.importGrammarLessonFromNotion(markdown));
            } catch (Exception e) {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
                );
            }
        }
        List<GrammarLessonEntity> savedGrammarLessonEntities = grammarLessonRepository.saveAll(grammarLessonEntities);
        return savedGrammarLessonEntities.stream()
                .map(grammarLessonEntity -> modelMapper.map(grammarLessonEntity, GrammarLessonResponse.class))
                .toList();
    }

    /**
     * @param file an excel file that contains
     *             column 1: lesson name
     *             column 2: grammar lesson id
     * @return list of lesson response after inserted lesson to the database
     */
    @Transactional
    public List<LessonResponse> importGrammarLessonsFromExcel(MultipartFile file) {
        if (!fileValidator.isExcelFile(file)) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL),
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL)
            );
        }
        List<LessonEntity> lessons = grammarLessonXlsxImporter.importGrammarLessonsFromExcel(file);
        return lessons.stream()
                .map(lessonEntity -> modelMapper.map(lessonEntity, LessonResponse.class))
                .toList();
    }
}
