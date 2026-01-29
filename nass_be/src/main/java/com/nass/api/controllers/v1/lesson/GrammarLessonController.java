package com.nass.api.controllers.v1.lesson;

import com.nass.application_service.dtos.responses.lesson.GrammarLessonResponse;
import com.nass.application_service.services.interfaces.lesson.IGrammarLessonService;
import com.nass.contract.annotations.ApiResponseFormat;
import com.nass.contract.constants.messages.lesson.GrammarLessonMessage;
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
@RequestMapping("/api/v1/grammar-lesson")
public class GrammarLessonController {
    private final IGrammarLessonService grammarLessonService;

    @ApiResponseFormat(devMessage = GrammarLessonMessage.GRAMMAR_LESSON_IMPORTED, clientMessage = GrammarLessonMessage.GRAMMAR_LESSON_IMPORTED)
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<GrammarLessonResponse>> importGrammarLessonsFromNotion(@RequestParam List<MultipartFile> files) {
        return ResponseEntity.status(HttpStatus.CREATED).body(grammarLessonService.importGrammarLessonsFromNotion(files));
    }
}
