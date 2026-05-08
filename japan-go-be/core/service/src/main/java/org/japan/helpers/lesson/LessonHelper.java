package org.japan.helpers.lesson;

import lombok.RequiredArgsConstructor;
import org.japan.entity.lesson.LessonEntity;
import org.japan.exceptions.NotFoundException;
import org.japan.i18n.I18nService;
import org.japan.message.lesson.LessonMessage;
import org.japan.repositories.lesson.LessonRepository;
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
