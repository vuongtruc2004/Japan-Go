package com.japan_go_be.features.grammar.exception;

import com.japan_go_be.common.exception.BaseException;

public class FolderException extends BaseException {
    public FolderException(String devMessage, String clientMessage) {
        super(devMessage, clientMessage);
    }
}
