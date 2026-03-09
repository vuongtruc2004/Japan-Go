package com.japan_go_be.api.controllers.v1.lesson;

import com.japan_go_be.business.dto.responses.base.PageDetailsResponse;
import com.japan_go_be.business.dto.responses.lesson.LessonResponse;
import com.japan_go_be.business.services.lesson.LessonService;
import com.japan_go_be.contract.annotations.ApiResponseFormat;
import com.japan_go_be.contract.constants.message.lesson.LessonMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lesson")
public class LessonController {
    private final LessonService lessonService;

    @ApiResponseFormat(devMessage = LessonMessage.LESSON_DELETED_ALL, clientMessage = LessonMessage.LESSON_DELETED_ALL)
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllLessons() {
        lessonService.deleteAllLessons();
        return ResponseEntity.ok().build();
    }

    @ApiResponseFormat(devMessage = LessonMessage.LESSON_DELETED, clientMessage = LessonMessage.LESSON_DELETED)
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteLesson(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.deleteLesson(id));
    }

    @ApiResponseFormat(devMessage = LessonMessage.LESSON_GET_BY_ID, clientMessage = LessonMessage.LESSON_GET_BY_ID)
    @GetMapping("/{id}")
    public ResponseEntity<LessonResponse> getLessonById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    @ApiResponseFormat(devMessage = LessonMessage.LESSON_GET_ALL, clientMessage = LessonMessage.LESSON_GET_ALL)
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<LessonResponse>>> getAllLessons(Pageable pageable) {
        return ResponseEntity.ok(lessonService.getAllLessons(pageable));
    }
}
