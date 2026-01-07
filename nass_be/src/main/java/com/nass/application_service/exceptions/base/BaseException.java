package com.nass.application_service.exceptions.base;

import com.nass.contract.constants.DefaultMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException {
    final String devMessage;
    final String userMessage;
    final String extraMessage;

    public BaseException(String devMessage, String userMessage, String extraMessage) {
        super(devMessage);
        this.devMessage = devMessage;
        this.userMessage = userMessage;
        this.extraMessage = extraMessage;
    }

    public BaseException(String devMessage, String userMessage) {
        super(devMessage);
        this.devMessage = devMessage;
        this.userMessage = userMessage;
        this.extraMessage = null;
    }

    public BaseException(String devMessage) {
        super(devMessage);
        this.devMessage = devMessage;
        this.userMessage = DefaultMessage.DEFAULT_ERROR_MESSAGE;
        this.extraMessage = null;
    }
}
