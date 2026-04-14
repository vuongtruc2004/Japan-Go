package com.japan_go_be.api.controllers.v1.lesson;

import com.japan_go_be.business.dto.requests.lesson.KanjiLessonRequest;
import com.japan_go_be.business.dto.responses.lesson.LessonResponse;
import com.japan_go_be.business.services.lesson.KanjiLessonService;
import com.japan_go_be.contract.annotations.ApiResponseFormat;
import com.japan_go_be.contract.message.lesson.KanjiLessonMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kanji-lessons")
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
