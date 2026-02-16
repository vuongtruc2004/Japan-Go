package com.japan_go_be.features.lesson.service;

import com.japan_go_be.common.constant.message.FileMessage;
import com.japan_go_be.common.exception.FileNotValidException;
import com.japan_go_be.common.exception.NotFoundException;
import com.japan_go_be.common.i18n.I18nService;
import com.japan_go_be.common.validator.FileValidator;
import com.japan_go_be.features.kanji.constant.messages.KanjiMessage;
import com.japan_go_be.features.kanji.dto.request.KanjiPageRequest;
import com.japan_go_be.features.kanji.entity.KanjiEntity;
import com.japan_go_be.features.kanji.entity.KanjiPageEntity;
import com.japan_go_be.features.kanji.importer.KanjiPageXlsxImporter;
import com.japan_go_be.features.kanji.repository.KanjiRepository;
import com.japan_go_be.features.lesson.constant.LessonTypeEnum;
import com.japan_go_be.features.lesson.dto.request.KanjiLessonRequest;
import com.japan_go_be.features.lesson.dto.response.LessonResponse;
import com.japan_go_be.features.lesson.entity.KanjiLessonEntity;
import com.japan_go_be.features.lesson.entity.LessonEntity;
import com.japan_go_be.features.lesson.mapper.LessonDtoMapper;
import com.japan_go_be.features.lesson.repository.LessonRepository;
import com.japan_go_be.features.vocabulary.dto.request.VocabularyRequest;
import com.japan_go_be.features.vocabulary.entity.VocabularyEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
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

    /**
     * Create kanji lesson (use by the create lesson method in lesson service)
     *
     * @param kanjiLessonRequest kanji lesson request object
     * @return kanji lesson entity to used by the create lesson method
     */
    public KanjiLessonEntity createKanjiLesson(KanjiLessonRequest kanjiLessonRequest) {
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

                vocabularyEntity.getKanjiPages().add(kanjiPageEntity);
                kanjiPageEntity.getVocabularies().add(vocabularyEntity);
            }

            kanjiPageEntity.getKanjiLessons().add(kanjiLessonEntity);
            kanjiLessonEntity.getKanjiPages().add(kanjiPageEntity);
        }
        return kanjiLessonEntity;
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
