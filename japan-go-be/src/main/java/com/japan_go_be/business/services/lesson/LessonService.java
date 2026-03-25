package com.japan_go_be.business.services.lesson;

import com.japan_go_be.business.dto.mappers.LessonDtoMapper;
import com.japan_go_be.business.dto.responses.base.PageDetailsResponse;
import com.japan_go_be.business.dto.responses.lesson.LessonResponse;
import com.japan_go_be.business.helpers.lesson.LessonHelper;
import com.japan_go_be.infrastructure.entities.common.FolderEntity;
import com.japan_go_be.infrastructure.entities.lesson.LessonEntity;
import com.japan_go_be.infrastructure.repositories.lesson.LessonRepository;
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
        lessonRepository.deleteAll();
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
