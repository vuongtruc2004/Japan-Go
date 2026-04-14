package com.japan_go_be.business.services.lesson;

import com.japan_go_be.business.dto.mappers.lesson.LessonDtoMapper;
import com.japan_go_be.business.dto.requests.lesson.ExportGrammarLessonRequest;
import com.japan_go_be.business.dto.requests.lesson.GrammarLessonRequest;
import com.japan_go_be.business.dto.responses.lesson.LessonResponse;
import com.japan_go_be.business.exceptions.FileNotValidException;
import com.japan_go_be.business.helpers.folder.FolderHelper;
import com.japan_go_be.business.helpers.grammar.GrammarHelper;
import com.japan_go_be.business.helpers.lesson.BookHelper;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.business.importers.grammar.GrammarLessonMarkdownImporter;
import com.japan_go_be.business.validators.FileValidator;
import com.japan_go_be.contract.message.FileMessage;
import com.japan_go_be.contract.utils.StringUtil;
import com.japan_go_be.infrastructure.entities.common.FolderEntity;
import com.japan_go_be.infrastructure.entities.grammar.GrammarEntity;
import com.japan_go_be.infrastructure.entities.grammar.SentenceEntity;
import com.japan_go_be.infrastructure.entities.lesson.BookEntity;
import com.japan_go_be.infrastructure.entities.lesson.GrammarLessonEntity;
import com.japan_go_be.infrastructure.entities.lesson.LessonEntity;
import com.japan_go_be.infrastructure.mappers.grammar.SentenceMapper;
import com.japan_go_be.infrastructure.repositories.lesson.GrammarLessonRepository;
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
    private final GrammarLessonRepository grammarLessonRepository;
    private final SentenceMapper sentenceMapper;
    private final StringUtil stringUtil;
    private final GrammarHelper grammarHelper;
    private final BookHelper bookHelper;

    /**
     * Create a grammar lesson by importing Markdown files from Notion
     *
     * @param request include files, bookId, folderId, jlptLevel
     * @return return list of lesson response (summary)
     */
    @Transactional
    public List<LessonResponse> createGrammarLessons(GrammarLessonRequest request) {
        List<LessonEntity> lessons = new ArrayList<>();

        FolderEntity folderEntity = null;
        if (request.folderId() != null) {
            folderEntity = folderHelper.getFolderById(request.folderId());
        }
        BookEntity book = bookHelper.getBookById(request.bookId());

        for (MultipartFile file : request.files()) {
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

                LessonEntity lesson = grammarLessonMarkdownImporter.getLessonFromMarkdown(markdown, request.jlptLevel());
                lesson.setBook(book);
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

    public String exportGrammarLessonsToQuizletText(ExportGrammarLessonRequest request) {
        StringBuilder sb = new StringBuilder();
        List<GrammarLessonEntity> grammarLessons = grammarLessonRepository.findAllById(request.grammarLessonIds());

        for (GrammarLessonEntity grammarLesson : grammarLessons) {
            List<SentenceEntity> examples = sentenceMapper.findAllByGrammarLessonId(grammarLesson.getId());
            List<GrammarEntity> grammars = grammarLesson.getGrammars();

            for (SentenceEntity example : examples) {
                String front = stringUtil.replaceAllHtmlTagWithSubstring(
                        example.getJapaneseHtml(),
                        "<span class='grammar-highlight'>",
                        "</span>",
                        " { ",
                        " } "
                );
                String back = example.getVietnameseRaw();
                sb.append(front).append("#").append(back).append("$");
            }

            for (GrammarEntity grammar : grammars) {
                String front = grammar.getGrammarTitle();
                String back = grammarHelper.createBackOfFlashcard(grammar);
                sb.append(front).append("#").append(back).append("$");
            }
        }
        return sb.toString();
    }
}
