package org.japan.service.lesson;

import lombok.RequiredArgsConstructor;
import org.japan.dto.mapper.lesson.LessonDtoMapper;
import org.japan.dto.response.base.PageDetailsResponse;
import org.japan.dto.response.lesson.LessonResponse;
import org.japan.entity.common.FolderEntity;
import org.japan.entity.lesson.LessonEntity;
import org.japan.helper.lesson.LessonHelper;
import org.japan.persistence.repository.lesson.LessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        Specification<LessonEntity> specification = (root, query, cb) -> {
            query.orderBy(cb.desc(root.get("createdTime")));
            return cb.conjunction();
        };

        Page<LessonEntity> page = lessonRepository.findAll(specification, pageable);
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
