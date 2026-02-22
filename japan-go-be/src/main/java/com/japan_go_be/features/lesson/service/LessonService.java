package com.japan_go_be.features.lesson.service;

import com.japan_go_be.common.dto.PageDetailsResponse;
import com.japan_go_be.features.folder.entity.FolderEntity;
import com.japan_go_be.features.lesson.dto.response.LessonResponse;
import com.japan_go_be.features.lesson.entity.LessonEntity;
import com.japan_go_be.features.lesson.helper.LessonHelper;
import com.japan_go_be.features.lesson.mapper.LessonDtoMapper;
import com.japan_go_be.features.lesson.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final LessonDtoMapper lessonDtoMapper;
    private final LessonHelper lessonHelper;

    public LessonResponse getLessonById(Long id) {
        LessonEntity lesson = lessonHelper.getLessonById(id);
        return lessonDtoMapper.lessonEntityToLessonResponseDetails(lesson);
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

    @Transactional
    public void deleteAllLessons() {
        lessonRepository.deleteAllLessonFolderRelations();
        lessonRepository.deleteAllLessons();
    }

    public PageDetailsResponse<List<LessonResponse>> getAllLessons(Pageable pageable) {
        Page<LessonEntity> page = lessonRepository.findAllOrderByLastModifiedTimeDesc(pageable);
        List<LessonResponse> lessonResponses = page.getContent()
                .stream().map(lessonDtoMapper::lessonEntityToLessonResponseSummary)
                .toList();

        return PageDetailsResponse.<List<LessonResponse>>builder()
                .currentPage(page.getNumber() + 1)
                .pageSize(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .content(lessonResponses)
                .build();
    }
}
