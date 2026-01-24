package com.nass.api.middleware;

import com.nass.application_service.dto.responses.base.ApiResponse;
import com.nass.application_service.exceptions.*;
import com.nass.application_service.exceptions.base.BaseException;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.contract.enums.messages.DefaultMessageEnum;
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
                i18nService.translation(DefaultMessageEnum.DEFAULT_ERROR.key),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
