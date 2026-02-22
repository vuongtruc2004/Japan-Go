package com.japan_go_be.features.lesson.service;

import com.japan_go_be.common.constant.message.FileMessage;
import com.japan_go_be.common.exception.FileNotValidException;
import com.japan_go_be.common.i18n.I18nService;
import com.japan_go_be.common.validator.FileValidator;
import com.japan_go_be.features.folder.entity.FolderEntity;
import com.japan_go_be.features.folder.helper.FolderHelper;
import com.japan_go_be.features.lesson.dto.response.LessonResponse;
import com.japan_go_be.features.lesson.entity.LessonEntity;
import com.japan_go_be.features.lesson.helper.GrammarLessonMarkdownImporter;
import com.japan_go_be.features.lesson.mapper.LessonDtoMapper;
import com.japan_go_be.features.lesson.repository.LessonRepository;
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
     *
     * @param folderId folder id to add these lessons into
     * @param files    list of markdown files, each file is a lesson
     * @return List of lesson response summary
     */
    @Transactional
    public List<LessonResponse> createGrammarLessons(Long folderId, List<MultipartFile> files) {
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
                LessonEntity lesson = grammarLessonMarkdownImporter.getLessonFromMarkdown(markdown);
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
