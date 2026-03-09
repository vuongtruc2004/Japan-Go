package com.japan_go_be.api.middlewares;

import com.japan_go_be.business.dto.responses.base.ApiResponse;
import com.japan_go_be.business.exception.BadRequestException;
import com.japan_go_be.business.exception.BaseException;
import com.japan_go_be.business.exception.FileNotValidException;
import com.japan_go_be.business.exception.NotFoundException;
import com.japan_go_be.business.exception.common.FolderException;
import com.japan_go_be.business.exception.kanji.KanjiException;
import com.japan_go_be.business.exception.lesson.GrammarLessonException;
import com.japan_go_be.business.exception.lesson.KanjiLessonException;
import com.japan_go_be.business.exception.lesson.LessonException;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.contract.constants.message.DefaultMessage;
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
