package com.nass.business.lesson.controller.v1;

import com.nass.business.lesson.constant.messages.LessonMessage;
import com.nass.business.lesson.dto.response.LessonDetailsResponse;
import com.nass.business.lesson.dto.response.LessonResponse;
import com.nass.business.lesson.service.LessonService;
import com.nass.common.annotation.ApiResponseFormat;
import com.nass.common.dto.PageDetailsResponse;
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

    @ApiResponseFormat(devMessage = LessonMessage.LESSON_DELETED, clientMessage = LessonMessage.LESSON_DELETED)
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteLesson(@PathVariable Integer id) {
        return ResponseEntity.ok(lessonService.deleteLesson(id));
    }

    @ApiResponseFormat(devMessage = LessonMessage.LESSON_GET_BY_ID, clientMessage = LessonMessage.LESSON_GET_BY_ID)
    @GetMapping("/{id}")
    public ResponseEntity<LessonDetailsResponse> getLessonById(@PathVariable Integer id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    @ApiResponseFormat(devMessage = LessonMessage.LESSON_GET_ALL, clientMessage = LessonMessage.LESSON_GET_ALL)
    @GetMapping
    public ResponseEntity<PageDetailsResponse<List<LessonResponse>>> getAllLessons(Pageable pageable) {
        return ResponseEntity.ok(lessonService.getAllLessons(pageable));
    }
}
