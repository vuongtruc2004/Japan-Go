package com.japan_go_be.business.services.lesson;

import com.japan_go_be.business.dto.mappers.LessonDtoMapper;
import com.japan_go_be.business.dto.responses.lesson.LessonResponse;
import com.japan_go_be.business.exceptions.FileNotValidException;
import com.japan_go_be.business.helpers.folder.FolderHelper;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.business.importers.grammar.GrammarLessonMarkdownImporter;
import com.japan_go_be.business.validators.FileValidator;
import com.japan_go_be.contract.constants.message.FileMessage;
import com.japan_go_be.infrastructure.entities.common.FolderEntity;
import com.japan_go_be.infrastructure.entities.lesson.LessonEntity;
import com.japan_go_be.infrastructure.repositories.lesson.LessonRepository;
import lombok.RequiredArgsConstructor;
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
    private final LessonDtoMapper lessonDtoMapper;
    private final LessonRepository lessonRepository;
    private final FolderHelper folderHelper;

    /**
     * Us
     *
     * @param folderId folder id to add these lessons into
     * @param files    list of markdown files, each file is a lesson
     * @return List of lesson response summary
     */
    @Transactional
    public List<LessonResponse> createGrammarLessons(
            Long folderId,
            Integer jlptLevel,
            List<MultipartFile> files
    ) {
        List<LessonEntity> lessons = new ArrayList<>();

        FolderEntity folderEntity = null;
        if (folderId != null) {
            folderEntity = folderHelper.getFolderById(folderId);
        }

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
                LessonEntity lesson = grammarLessonMarkdownImporter.getLessonFromMarkdown(markdown, jlptLevel);
                lessons.add(lesson);
                if (folderEntity != null) {
                    folderEntity.getLessons().add(lesson);
                    lesson.getFolders().add(folderEntity);
                }

            } catch (Exception e) {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
                );
            }
        }
        List<LessonEntity> savedLessons = lessonRepository.saveAll(lessons);
        return savedLessons.stream()
                .map(lessonDtoMapper::lessonEntityToLessonResponseSummary)
                .toList();
    }
}
