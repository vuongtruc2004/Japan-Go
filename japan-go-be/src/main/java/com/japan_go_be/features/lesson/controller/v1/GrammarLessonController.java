package com.japan_go_be.features.lesson.controller.v1;

import com.japan_go_be.common.annotation.ApiResponseFormat;
import com.japan_go_be.features.lesson.constant.messages.GrammarLessonMessage;
import com.japan_go_be.features.lesson.constant.messages.LessonMessage;
import com.japan_go_be.features.lesson.dto.response.GrammarLessonResponse;
import com.japan_go_be.features.lesson.dto.response.LessonResponse;
import com.japan_go_be.features.lesson.service.GrammarLessonService;
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
    private final GrammarLessonService grammarLessonService;

    @ApiResponseFormat(devMessage = GrammarLessonMessage.GRAMMAR_LESSON_IMPORTED, clientMessage = GrammarLessonMessage.GRAMMAR_LESSON_IMPORTED)
    @PostMapping(value = "/import-notion", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<GrammarLessonResponse>> importGrammarLessonsFromNotion(@RequestParam List<MultipartFile> files) {
        return ResponseEntity.status(HttpStatus.CREATED).body(grammarLessonService.importGrammarLessonsFromNotion(files));
    }

    @ApiResponseFormat(devMessage = LessonMessage.LESSON_IMPORTED, clientMessage = LessonMessage.LESSON_IMPORTED)
    @PostMapping(value = "/import-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<LessonResponse>> importGrammarLessonsFromExcel(@RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(grammarLessonService.importGrammarLessonsFromExcel(file));
    }
}
