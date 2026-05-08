package org.japan.exception;

public class FileNotValidException extends BaseException {
    public FileNotValidException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
