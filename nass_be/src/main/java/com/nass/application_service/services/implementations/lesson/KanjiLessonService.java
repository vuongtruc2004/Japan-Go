package com.nass.application_service.services.implementations.lesson;

import com.nass.application_service.dtos.mappers.LessonDtoMapper;
import com.nass.application_service.dtos.responses.lesson.LessonDetailsResponse;
import com.nass.application_service.dtos.responses.lesson.LessonResponse;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.exceptions.NotFoundException;
import com.nass.application_service.exceptions.lesson.KanjiLessonException;
import com.nass.application_service.importers.kanji.KanjiPageXlsxImporter;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.application_service.services.interfaces.lesson.IKanjiLessonService;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.messages.common.FileMessage;
import com.nass.contract.constants.messages.lesson.LessonMessage;
import com.nass.contract.enums.lesson.LessonTypeEnum;
import com.nass.infrastructure.entities.kanji.KanjiPageEntity;
import com.nass.infrastructure.entities.lesson.KanjiLessonEntity;
import com.nass.infrastructure.entities.lesson.LessonEntity;
import com.nass.infrastructure.repositories.lesson.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KanjiLessonService implements IKanjiLessonService {
    private final FileValidator fileValidator;
    private final I18nService i18nService;
    private final KanjiPageXlsxImporter kanjiPageXlsxImporter;
    private final ModelMapper modelMapper;
    private final LessonRepository lessonRepository;
    private final LessonDtoMapper lessonDtoMapper;

    @Override
    public LessonDetailsResponse getKanjiLessonById(Integer id) {
        if (id == null) {
            throw new KanjiLessonException(
                    i18nService.translation(LessonMessage.LESSON_ID_NULL),
                    i18nService.translation(LessonMessage.LESSON_ID_NULL)
            );
        }
        LessonEntity lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        i18nService.translation(LessonMessage.LESSON_NOT_FOUND, id),
                        i18nService.translation(LessonMessage.LESSON_NOT_FOUND, id)
                ));
        return lessonDtoMapper.lessonEntityToLessonDetailsResponse(lesson);
    }

    @Override
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
                    .lessonName(file.getOriginalFilename())
                    .kanjiLesson(kanjiLesson)
                    .build();
            kanjiLesson.setLesson(lesson);

            LessonEntity savedLesson = lessonRepository.save(lesson);
            return modelMapper.map(savedLesson, LessonResponse.class);

        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }
}
