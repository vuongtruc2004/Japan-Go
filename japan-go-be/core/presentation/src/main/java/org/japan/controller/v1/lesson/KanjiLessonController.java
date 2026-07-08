package org.japan.controller.v1.lesson;

import lombok.RequiredArgsConstructor;
import org.japan.annotations.ApiResponseFormat;
import org.japan.dto.request.lesson.KanjiLessonImportRequest;
import org.japan.dto.response.lesson.LessonResponse;
import org.japan.message.lesson.KanjiLessonMessage;
import org.japan.service.lesson.KanjiLessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kanji-lessons")
@RequiredArgsConstructor
public class KanjiLessonController {
    private final KanjiLessonService kanjiLessonService;

    @ApiResponseFormat(
            devMessage = KanjiLessonMessage.KANJI_LESSON_IMPORTED,
            clientMessage = KanjiLessonMessage.KANJI_LESSON_IMPORTED
    )
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LessonResponse> importKanjiLessonFromExcel(
            @ModelAttribute KanjiLessonImportRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(kanjiLessonService.importKanjiLessonFromExcel(request));
    }
}
