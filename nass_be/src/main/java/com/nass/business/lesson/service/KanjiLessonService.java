package com.nass.business.lesson.service;

import com.nass.business.kanji.entity.KanjiPageEntity;
import com.nass.business.kanji.importer.KanjiPageXlsxImporter;
import com.nass.business.lesson.constant.LessonTypeEnum;
import com.nass.business.lesson.dto.response.LessonResponse;
import com.nass.business.lesson.entity.KanjiLessonEntity;
import com.nass.business.lesson.entity.LessonEntity;
import com.nass.business.lesson.repository.LessonRepository;
import com.nass.common.constant.FileMessage;
import com.nass.common.exception.FileNotValidException;
import com.nass.common.i18n.I18nService;
import com.nass.common.validator.FileValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;
    private final LessonRepository lessonRepository;

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
