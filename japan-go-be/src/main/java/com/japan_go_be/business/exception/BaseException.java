package com.japan_go_be.business.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException {
    String devMessage;
    String clientMessage;

    public BaseException(String devMessage, String clientMessage) {
        super(devMessage);
        this.devMessage = devMessage;
        this.clientMessage = clientMessage;
    }
}
