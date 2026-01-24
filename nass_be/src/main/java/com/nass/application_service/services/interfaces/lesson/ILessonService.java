package com.nass.application_service.services.interfaces.lesson;

import com.nass.application_service.dto.responses.lesson.LessonResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ILessonService {
    List<LessonResponse> importLessonsFromExcel(MultipartFile file);
}
