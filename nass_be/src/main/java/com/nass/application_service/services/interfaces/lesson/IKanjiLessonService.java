package com.nass.application_service.services.interfaces.lesson;

import com.nass.application_service.dtos.responses.lesson.LessonDetailsResponse;
import com.nass.application_service.dtos.responses.lesson.LessonResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IKanjiLessonService {
    LessonResponse importKanjiLessonFromExcel(MultipartFile file);

    LessonDetailsResponse getKanjiLessonById(Integer id);
}
