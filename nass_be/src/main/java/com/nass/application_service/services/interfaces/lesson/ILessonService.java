package com.nass.application_service.services.interfaces.lesson;

import com.nass.application_service.dtos.responses.lesson.LessonResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ILessonService {
    List<LessonResponse> importGrammarLessonsFromExcel(MultipartFile file);
}
