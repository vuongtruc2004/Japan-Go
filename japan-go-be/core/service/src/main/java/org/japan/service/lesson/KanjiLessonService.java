package org.japan.service.lesson;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.japan.constants.lesson.LessonTypeEnum;
import org.japan.dto.mapper.lesson.LessonDtoMapper;
import org.japan.dto.request.common.FolderLessonRequest;
import org.japan.dto.request.kanji.KanjiPageRequest;
import org.japan.dto.request.kanji.VocabularyRequest;
import org.japan.dto.request.lesson.KanjiLessonRequest;
import org.japan.dto.response.lesson.LessonResponse;
import org.japan.entity.kanji.KanjiEntity;
import org.japan.entity.kanji.KanjiPageEntity;
import org.japan.entity.kanji.VocabularyEntity;
import org.japan.entity.lesson.BookEntity;
import org.japan.entity.lesson.KanjiLessonEntity;
import org.japan.entity.lesson.LessonEntity;
import org.japan.exception.FileNotValidException;
import org.japan.exception.NotFoundException;
import org.japan.exception.lesson.LessonException;
import org.japan.helper.lesson.BookHelper;
import org.japan.i18n.I18nService;
import org.japan.importer.kanji.KanjiPageXlsxImporter;
import org.japan.message.FileMessage;
import org.japan.message.kanji.KanjiMessage;
import org.japan.message.lesson.LessonMessage;
import org.japan.persistence.repository.kanji.KanjiRepository;
import org.japan.persistence.repository.lesson.LessonRepository;
import org.japan.service.common.FolderService;
import org.japan.validator.FileValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KanjiLessonService {
    private final FileValidator fileValidator;
    private final I18nService i18nService;
    private final KanjiPageXlsxImporter kanjiPageXlsxImporter;
    private final LessonRepository lessonRepository;
    private final KanjiRepository kanjiRepository;
    private final LessonDtoMapper lessonDtoMapper;
    private final FolderService folderService;
    private final BookHelper bookHelper;

    @Transactional
    public LessonResponse createKanjiLesson(KanjiLessonRequest kanjiLessonRequest) {
        if (kanjiLessonRequest.lessonId() != null) {
            throw new LessonException(
                    i18nService.translation(LessonMessage.LESSON_ID_MUST_BE_NULL),
                    i18nService.translation(LessonMessage.LESSON_ID_MUST_BE_NULL)
            );
        }
        BookEntity book = bookHelper.getBookById(kanjiLessonRequest.bookId());

        LessonEntity lesson = LessonEntity.builder()
                .lessonName(kanjiLessonRequest.lessonName())
                .description(kanjiLessonRequest.description())
                .lessonType(kanjiLessonRequest.lessonType())
                .book(book)
                .build();

        if (lesson.getLessonType().equals(LessonTypeEnum.KANJI)) {
            KanjiLessonEntity kanjiLessonEntity = new KanjiLessonEntity();

            for (KanjiPageRequest kanjiPageRequest : kanjiLessonRequest.kanjiPages()) {
                String mainKanjiCharacter = kanjiPageRequest.mainKanjiCharacter();

                KanjiEntity mainKanji = kanjiRepository.findByKanjiCharacter(mainKanjiCharacter)
                        .orElseThrow(() -> new NotFoundException(
                                i18nService.translation(KanjiMessage.KANJI_CHARACTER_NOT_FOUND, mainKanjiCharacter),
                                i18nService.translation(KanjiMessage.KANJI_CHARACTER_NOT_FOUND, mainKanjiCharacter)
                        ));
                KanjiPageEntity kanjiPageEntity = KanjiPageEntity.builder()
                        .mainKanji(mainKanji)
                        .build();

                for (VocabularyRequest vocabularyRequest : kanjiPageRequest.vocabularies()) {
                    VocabularyEntity vocabularyEntity = VocabularyEntity.builder()
                            .japanese(vocabularyRequest.japanese())
                            .reading(vocabularyRequest.reading())
                            .meaning(vocabularyRequest.meaning())
                            .note(vocabularyRequest.note())
                            .build();

                    vocabularyEntity.setKanjiPage(kanjiPageEntity);
                    kanjiPageEntity.getVocabularies().add(vocabularyEntity);
                }

                kanjiPageEntity.setKanjiLesson(kanjiLessonEntity);
                kanjiLessonEntity.getKanjiPages().add(kanjiPageEntity);
            }
            lesson.setKanjiLesson(kanjiLessonEntity);
        } else {
            throw new LessonException(
                    i18nService.translation(LessonMessage.LESSON_TYPE_INVALID),
                    i18nService.translation(LessonMessage.LESSON_TYPE_INVALID)
            );
        }

        LessonEntity savedLesson = lessonRepository.save(lesson);
        if (kanjiLessonRequest.folderId() != null) {
            folderService.addLessonToFolder(new FolderLessonRequest(kanjiLessonRequest.folderId(), savedLesson.getId()));
        }
        return lessonDtoMapper.lessonEntityToLessonResponseDetails(savedLesson);
    }

    @Transactional
    public LessonResponse importKanjiLessonFromExcel(MultipartFile file) {
        if (!fileValidator.isExcelFile(file)) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL),
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL)
            );
        }

        try (InputStream inputStream = file.getInputStream()) {
            List<KanjiPageEntity> kanjiPages = kanjiPageXlsxImporter.importKanjiPage(inputStream);
            KanjiLessonEntity kanjiLesson = KanjiLessonEntity.builder()
                    .kanjiPages(kanjiPages)
                    .build();

            LessonEntity lesson = LessonEntity.builder()
                    .lessonType(LessonTypeEnum.KANJI)
                    .lessonName(FilenameUtils.getBaseName(file.getOriginalFilename()))
                    .kanjiLesson(kanjiLesson)
                    .build();
            kanjiLesson.setLesson(lesson);

            LessonEntity savedLesson = lessonRepository.save(lesson);
            return lessonDtoMapper.lessonEntityToLessonResponseSummary(savedLesson);

        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }
}
