package org.japan.exceptions;

public class NotFoundException extends BaseException {
    public NotFoundException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
