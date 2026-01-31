package com.nass.api.controllers.v1.lesson;

import com.nass.application_service.dtos.responses.lesson.LessonDetailsResponse;
import com.nass.application_service.dtos.responses.lesson.LessonResponse;
import com.nass.application_service.services.interfaces.lesson.IKanjiLessonService;
import com.nass.contract.annotations.ApiResponseFormat;
import com.nass.contract.constants.messages.lesson.KanjiLessonMessage;
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
    public final IKanjiLessonService kanjiLessonService;

    @ApiResponseFormat(devMessage = KanjiLessonMessage.KANJI_LESSON_IMPORTED, clientMessage = KanjiLessonMessage.KANJI_LESSON_IMPORTED)
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LessonResponse> importKanjiLessonFromExcel(@RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kanjiLessonService.importKanjiLessonFromExcel(file));
    }

    @ApiResponseFormat(devMessage = KanjiLessonMessage.KANJI_LESSON_GET_BY_ID, clientMessage = KanjiLessonMessage.KANJI_LESSON_GET_BY_ID)
    @GetMapping("/{id}")
    public ResponseEntity<LessonDetailsResponse> getKanjiLessonById(@PathVariable Integer id) {
        return ResponseEntity.ok(kanjiLessonService.getKanjiLessonById(id));
    }
}
