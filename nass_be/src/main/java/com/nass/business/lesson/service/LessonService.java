package com.nass.business.lesson.service;

import com.nass.business.lesson.dto.response.LessonDetailsResponse;
import com.nass.business.lesson.dto.response.LessonResponse;
import com.nass.business.lesson.entity.LessonEntity;
import com.nass.business.lesson.helper.LessonHelper;
import com.nass.business.lesson.mapper.LessonDtoMapper;
import com.nass.business.lesson.repository.LessonRepository;
import com.nass.common.dto.PageDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final LessonDtoMapper lessonDtoMapper;
    private final LessonHelper lessonHelper;
    private final ModelMapper modelMapper;

    public LessonDetailsResponse getLessonById(Integer id) {
        LessonEntity lesson = lessonHelper.getLessonById(id);
        return lessonDtoMapper.lessonEntityToLessonDetailsResponse(lesson);
    }

    public Integer deleteLesson(Integer id) {
        LessonEntity lesson = lessonHelper.getLessonById(id);
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
