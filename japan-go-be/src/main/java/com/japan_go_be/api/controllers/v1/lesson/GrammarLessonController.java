package com.japan_go_be.api.controllers.v1.lesson;

import com.japan_go_be.business.dto.responses.lesson.LessonResponse;
import com.japan_go_be.business.services.lesson.GrammarLessonService;
import com.japan_go_be.contract.annotations.ApiResponseFormat;
import com.japan_go_be.contract.constants.message.lesson.GrammarLessonMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/grammar-lesson")
public class GrammarLessonController {
    private final GrammarLessonService grammarLessonService;

    @ApiResponseFormat(devMessage = GrammarLessonMessage.GRAMMAR_LESSON_CREATE_SUCCESS, clientMessage = GrammarLessonMessage.GRAMMAR_LESSON_CREATE_SUCCESS)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<LessonResponse>> createGrammarLessons(
            @RequestParam(value = "folderId", required = false) Long folderId,
            @RequestParam Integer jlptLevel,
            @RequestPart("files") List<MultipartFile> files) {
        return ResponseEntity.status(HttpStatus.CREATED).body(grammarLessonService.createGrammarLessons(folderId, jlptLevel, files));
    }
}
