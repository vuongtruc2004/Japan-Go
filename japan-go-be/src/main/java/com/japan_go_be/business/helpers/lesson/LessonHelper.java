package com.japan_go_be.business.helpers.lesson;

import com.japan_go_be.business.exceptions.NotFoundException;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.contract.constants.message.lesson.LessonMessage;
import com.japan_go_be.infrastructure.entities.lesson.LessonEntity;
import com.japan_go_be.infrastructure.repositories.lesson.LessonRepository;
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
