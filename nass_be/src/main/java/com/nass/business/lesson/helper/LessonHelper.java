package com.nass.business.lesson.helper;

import com.nass.business.lesson.constant.messages.LessonMessage;
import com.nass.business.lesson.entity.LessonEntity;
import com.nass.business.lesson.repository.LessonRepository;
import com.nass.common.exception.NotFoundException;
import com.nass.common.i18n.I18nService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonHelper {
    private final LessonRepository lessonRepository;
    private final I18nService i18nService;

    public LessonEntity getLessonById(Integer id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        i18nService.translation(LessonMessage.LESSON_NOT_FOUND, id),
                        i18nService.translation(LessonMessage.LESSON_NOT_FOUND, id)
                ));
    }
}
