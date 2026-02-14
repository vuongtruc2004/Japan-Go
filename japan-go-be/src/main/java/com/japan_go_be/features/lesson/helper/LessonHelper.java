package com.japan_go_be.features.lesson.helper;

import com.japan_go_be.common.exception.NotFoundException;
import com.japan_go_be.common.i18n.I18nService;
import com.japan_go_be.features.lesson.constant.messages.LessonMessage;
import com.japan_go_be.features.lesson.entity.LessonEntity;
import com.japan_go_be.features.lesson.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonHelper {
    private final LessonRepository lessonRepository;
    private final I18nService i18nService;

    public LessonEntity getLessonById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        i18nService.translation(LessonMessage.LESSON_NOT_FOUND, id),
                        i18nService.translation(LessonMessage.LESSON_NOT_FOUND, id)
                ));
    }
}
