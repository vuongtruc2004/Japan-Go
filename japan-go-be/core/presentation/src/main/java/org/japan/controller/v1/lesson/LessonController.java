package org.japan.controller.v1.lesson;

import lombok.RequiredArgsConstructor;
import org.japan.annotations.ApiResponseFormat;
import org.japan.dto.response.base.PageDetailsResponse;
import org.japan.dto.response.lesson.LessonResponse;
import org.japan.message.lesson.LessonMessage;
import org.japan.service.lesson.LessonService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lessons")
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
