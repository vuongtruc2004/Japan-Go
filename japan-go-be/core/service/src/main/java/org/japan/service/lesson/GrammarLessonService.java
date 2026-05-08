package org.japan.service.lesson;

import lombok.RequiredArgsConstructor;
import org.japan.dto.mapper.lesson.LessonDtoMapper;
import org.japan.dto.request.lesson.ExportGrammarLessonRequest;
import org.japan.dto.request.lesson.GrammarLessonRequest;
import org.japan.dto.response.lesson.LessonResponse;
import org.japan.entity.common.FolderEntity;
import org.japan.entity.grammar.GrammarEntity;
import org.japan.entity.grammar.SentenceEntity;
import org.japan.entity.lesson.BookEntity;
import org.japan.entity.lesson.GrammarLessonEntity;
import org.japan.entity.lesson.LessonEntity;
import org.japan.exception.FileNotValidException;
import org.japan.helper.folder.FolderHelper;
import org.japan.helper.grammar.GrammarHelper;
import org.japan.helper.lesson.BookHelper;
import org.japan.i18n.I18nService;
import org.japan.importer.grammar.GrammarLessonMarkdownImporter;
import org.japan.persistence.mybatis.grammar.SentenceMapper;
import org.japan.message.FileMessage;
import org.japan.persistence.repository.lesson.GrammarLessonRepository;
import org.japan.persistence.repository.lesson.LessonRepository;
import org.japan.utils.StringUtil;
import org.japan.validator.FileValidator;
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
