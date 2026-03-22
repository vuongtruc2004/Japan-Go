package com.japan_go_be.business.exceptions.common;

import com.japan_go_be.business.exceptions.BaseException;

public class FolderException extends BaseException {
    public FolderException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
