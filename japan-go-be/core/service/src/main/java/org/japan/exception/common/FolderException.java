package org.japan.exception.common;

import org.japan.exception.BaseException;

public class FolderException extends BaseException {
    public FolderException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
