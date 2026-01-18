package com.nass.application_service.services.interfaces;

import com.nass.application_service.dto.responses.grammar.LessonResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IGrammarService {
    List<LessonResponse> importLessonsFromNotion(List<MultipartFile> files);

    byte[] exportLessonsToExcel();
}
