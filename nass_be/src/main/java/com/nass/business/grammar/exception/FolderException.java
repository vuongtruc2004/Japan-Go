package com.nass.business.grammar.exception;

import com.nass.common.exception.BaseException;

public class FolderException extends BaseException {
    public FolderException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
