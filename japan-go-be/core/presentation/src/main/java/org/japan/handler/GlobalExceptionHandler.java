package org.japan.handler;

import lombok.RequiredArgsConstructor;
import org.japan.dto.response.base.ApiResponse;
import org.japan.exceptions.BadRequestException;
import org.japan.exceptions.BaseException;
import org.japan.exceptions.FileNotValidException;
import org.japan.exceptions.NotFoundException;
import org.japan.exceptions.common.FolderException;
import org.japan.exceptions.kanji.KanjiException;
import org.japan.exceptions.lesson.GrammarLessonException;
import org.japan.exceptions.lesson.KanjiLessonException;
import org.japan.exceptions.lesson.LessonException;
import org.japan.i18n.I18nService;
import org.japan.message.DefaultMessage;
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
