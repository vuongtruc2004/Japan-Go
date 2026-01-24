package com.nass.application_service.helpers.common;

import org.springframework.stereotype.Service;

@Service
public class ParseHelper {
    public Integer parseStringToInteger(String string) {
        try {
            return Integer.parseInt(string.trim());
        } catch (Exception e) {
            throw new NumberFormatException(e.getMessage());
        }
    }
}
