package org.japan.controllers.v1.lesson;

import lombok.RequiredArgsConstructor;
import org.japan.annotations.ApiResponseFormat;
import org.japan.dto.request.lesson.KanjiLessonRequest;
import org.japan.dto.response.lesson.LessonResponse;
import org.japan.message.lesson.KanjiLessonMessage;
import org.japan.services.lesson.KanjiLessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/kanji-lessons")
@RequiredArgsConstructor
public class KanjiLessonController {
    private final KanjiLessonService kanjiLessonService;
    
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
