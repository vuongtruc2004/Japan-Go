package com.nass.api.controllers.v1.lesson;

import com.nass.application_service.dtos.responses.lesson.LessonResponse;
import com.nass.application_service.services.interfaces.lesson.ILessonService;
import com.nass.contract.annotations.ApiResponseFormat;
import com.nass.contract.constants.messages.lesson.LessonMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lesson")
public class LessonController {
    private final ILessonService lessonService;

    @ApiResponseFormat(devMessage = LessonMessage.LESSON_IMPORTED, clientMessage = LessonMessage.LESSON_IMPORTED)
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<LessonResponse>> importGrammarLessonsFromExcel(@RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.importGrammarLessonsFromExcel(file));
    }
}
