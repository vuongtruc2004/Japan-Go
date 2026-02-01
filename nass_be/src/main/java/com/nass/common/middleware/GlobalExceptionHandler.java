package com.nass.common.middleware;

import com.nass.business.folder.exception.FolderException;
import com.nass.common.i18n.I18nService;
import com.nass.business.kanji.exception.KanjiException;
import com.nass.business.lesson.exception.GrammarLessonException;
import com.nass.business.lesson.exception.KanjiLessonException;
import com.nass.common.constant.DefaultMessage;
import com.nass.common.dto.ApiResponse;
import com.nass.common.exception.BadRequestException;
import com.nass.common.exception.BaseException;
import com.nass.common.exception.FileNotValidException;
import com.nass.common.exception.NotFoundException;
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
