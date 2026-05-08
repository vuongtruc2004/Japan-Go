package org.japan.controller.v1.lesson;

import lombok.RequiredArgsConstructor;
import org.japan.annotations.ApiResponseFormat;
import org.japan.dto.request.lesson.ExportGrammarLessonRequest;
import org.japan.dto.request.lesson.GrammarLessonRequest;
import org.japan.dto.response.lesson.LessonResponse;
import org.japan.message.lesson.GrammarLessonMessage;
import org.japan.service.lesson.GrammarLessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/grammar-lessons")
public class GrammarLessonController {
    private final GrammarLessonService grammarLessonService;

    @ApiResponseFormat(devMessage = GrammarLessonMessage.GRAMMAR_LESSON_CREATE_SUCCESS, clientMessage = GrammarLessonMessage.GRAMMAR_LESSON_CREATE_SUCCESS)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<LessonResponse>> createGrammarLessons(
            @ModelAttribute GrammarLessonRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(grammarLessonService.createGrammarLessons(request));
    }

    @ApiResponseFormat(
            devMessage = GrammarLessonMessage.GRAMMAR_LESSON_EXPORT_QUIZLET_SUCCESS,
            clientMessage = GrammarLessonMessage.GRAMMAR_LESSON_EXPORT_QUIZLET_SUCCESS
    )
    @PostMapping(value = "/export-quizlet", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> exportGrammarLessonsToQuizletText(
            @RequestBody ExportGrammarLessonRequest request
    ) {
        String text = grammarLessonService.exportGrammarLessonsToQuizletText(request);
        return ResponseEntity.ok(text);
    }
}
