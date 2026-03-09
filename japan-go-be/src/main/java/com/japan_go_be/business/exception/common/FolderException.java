package com.japan_go_be.business.exception.common;

import com.japan_go_be.business.exception.BaseException;

public class FolderException extends BaseException {
    public FolderException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
