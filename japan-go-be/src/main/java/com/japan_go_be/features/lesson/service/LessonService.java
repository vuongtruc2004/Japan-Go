package com.japan_go_be.features.lesson.service;

import com.japan_go_be.common.dto.PageDetailsResponse;
import com.japan_go_be.common.i18n.I18nService;
import com.japan_go_be.features.folder.entity.FolderEntity;
import com.japan_go_be.features.lesson.constant.LessonTypeEnum;
import com.japan_go_be.features.lesson.constant.messages.LessonMessage;
import com.japan_go_be.features.lesson.dto.request.LessonRequest;
import com.japan_go_be.features.lesson.dto.response.LessonDetailsResponse;
import com.japan_go_be.features.lesson.dto.response.LessonResponse;
import com.japan_go_be.features.lesson.entity.KanjiLessonEntity;
import com.japan_go_be.features.lesson.entity.LessonEntity;
import com.japan_go_be.features.lesson.exception.LessonException;
import com.japan_go_be.features.lesson.helper.LessonHelper;
import com.japan_go_be.features.lesson.mapper.LessonDtoMapper;
import com.japan_go_be.features.lesson.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final LessonDtoMapper lessonDtoMapper;
    private final LessonHelper lessonHelper;
    private final ModelMapper modelMapper;
    private final I18nService i18nService;
    private final KanjiLessonService kanjiLessonService;

    public LessonResponse createLesson(LessonRequest lessonRequest) {
        if (lessonRequest.id() != null) {
            throw new LessonException(
                    i18nService.translation(LessonMessage.LESSON_ID_MUST_BE_NULL),
                    i18nService.translation(LessonMessage.LESSON_ID_MUST_BE_NULL)
            );
        }
        LessonEntity lesson = LessonEntity.builder()
                .lessonName(lessonRequest.lessonName())
                .description(lessonRequest.description())
                .lessonType(lessonRequest.lessonType())
                .build();
        if (lesson.getLessonType().equals(LessonTypeEnum.KANJI) && lessonRequest.kanjiLesson() != null && lessonRequest.grammarLesson() == null) {
            KanjiLessonEntity kanjiLessonEntity = kanjiLessonService.createKanjiLesson(lessonRequest.kanjiLesson());
            lesson.setKanjiLesson(kanjiLessonEntity);
        } else if (lesson.getLessonType().equals(LessonTypeEnum.GRAMMAR) && lessonRequest.kanjiLesson() == null && lessonRequest.grammarLesson() != null) {

        } else {
            throw new LessonException(
                    i18nService.translation(LessonMessage.LESSON_TYPE_INVALID),
                    i18nService.translation(LessonMessage.LESSON_TYPE_INVALID)
            );
        }

        LessonEntity savedLesson = lessonRepository.save(lesson);
        return modelMapper.map(savedLesson, LessonResponse.class);
    }

    public LessonDetailsResponse getLessonById(Long id) {
        LessonEntity lesson = lessonHelper.getLessonById(id);
        return lessonDtoMapper.lessonEntityToLessonDetailsResponse(lesson);
    }

    public Long deleteLesson(Long id) {
        LessonEntity lesson = lessonHelper.getLessonById(id);
        for (FolderEntity folder : new ArrayList<>(lesson.getFolders())) {
            folder.getLessons().remove(lesson);
            lesson.getFolders().remove(folder);
        }
        lessonRepository.delete(lesson);
        return id;
    }

    public PageDetailsResponse<List<LessonResponse>> getAllLessons(Pageable pageable) {
        Page<LessonEntity> page = lessonRepository.findAll(pageable);
        List<LessonResponse> lessonResponses = page.getContent()
                .stream().map(lessonEntity -> modelMapper.map(lessonEntity, LessonResponse.class))
                .toList();

        return PageDetailsResponse.<List<LessonResponse>>builder()
                .currentPage(page.getNumber() + 1)
                .pageSize(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .content(lessonResponses)
                .build();
    }
}
