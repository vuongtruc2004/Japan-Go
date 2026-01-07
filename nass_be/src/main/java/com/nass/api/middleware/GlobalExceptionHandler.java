package com.nass.api.middleware;

import com.nass.application_service.dto.response.base.ApiResponse;
import com.nass.application_service.exceptions.NotFoundException;
import com.nass.application_service.exceptions.base.BaseException;
import com.nass.contract.constants.DefaultMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ApiResponse<Void>> handleBadRequestBaseException(BaseException exception) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "DEV: " + exception.getDevMessage(),
                exception.getUserMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnknownException(Exception exception) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "DEV: " + exception.getMessage(),
                DefaultMessage.DEFAULT_ERROR_MESSAGE,
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
