package com.nass.application_service.services.interfaces.lesson;

import com.nass.application_service.dtos.responses.lesson.GrammarLessonResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IGrammarLessonService {
    List<GrammarLessonResponse> importGrammarLessonsFromNotion(List<MultipartFile> files);
}
