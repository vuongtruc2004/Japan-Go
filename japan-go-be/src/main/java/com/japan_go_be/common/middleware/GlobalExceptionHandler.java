package com.japan_go_be.common.middleware;

import com.japan_go_be.common.constant.DefaultMessage;
import com.japan_go_be.common.dto.ApiResponse;
import com.japan_go_be.common.exception.BadRequestException;
import com.japan_go_be.common.exception.BaseException;
import com.japan_go_be.common.exception.FileNotValidException;
import com.japan_go_be.common.exception.NotFoundException;
import com.japan_go_be.common.i18n.I18nService;
import com.japan_go_be.features.folder.exception.FolderException;
import com.japan_go_be.features.kanji.exception.KanjiException;
import com.japan_go_be.features.lesson.exception.GrammarLessonException;
import com.japan_go_be.features.lesson.exception.KanjiLessonException;
import com.japan_go_be.features.lesson.exception.LessonException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final I18nService i18nService;

    @ExceptionHandler(value = {
            NotFoundException.class,
            FileNotValidException.class,
            BadRequestException.class,
            FolderException.class,
            KanjiException.class,
            GrammarLessonException.class,
            KanjiLessonException.class,
            LessonException.class
    })
    public ResponseEntity<ApiResponse<Void>> handleBadRequestBaseException(BaseException exception) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                exception.getDevMessage(),
                exception.getClientMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnknownException(Exception exception) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                i18nService.translation(DefaultMessage.DEFAULT_ERROR),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
