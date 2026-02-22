package com.japan_go_be.features.lesson.controller.v1;

import com.japan_go_be.common.annotation.ApiResponseFormat;
import com.japan_go_be.features.lesson.constant.messages.KanjiLessonMessage;
import com.japan_go_be.features.lesson.dto.request.KanjiLessonRequest;
import com.japan_go_be.features.lesson.dto.response.LessonResponse;
import com.japan_go_be.features.lesson.service.KanjiLessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kanji-lesson")
public class KanjiLessonController {
    public final KanjiLessonService kanjiLessonService;

    @ApiResponseFormat(devMessage = KanjiLessonMessage.KANJI_LESSON_CREATE_SUCCESS, clientMessage = KanjiLessonMessage.KANJI_LESSON_CREATE_SUCCESS)
    @PostMapping
    public ResponseEntity<LessonResponse> createKanjiLesson(@RequestBody KanjiLessonRequest kanjiLessonRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kanjiLessonService.createKanjiLesson(kanjiLessonRequest));
    }

    @ApiResponseFormat(devMessage = KanjiLessonMessage.KANJI_LESSON_IMPORTED, clientMessage = KanjiLessonMessage.KANJI_LESSON_IMPORTED)
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LessonResponse> importKanjiLessonFromExcel(@RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kanjiLessonService.importKanjiLessonFromExcel(file));
    }
}
