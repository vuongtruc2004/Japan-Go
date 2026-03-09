package com.japan_go_be.contract.utils;

import org.springframework.stereotype.Service;

@Service
public class ParseUtil {
    public Integer parseStringToInteger(String string) {
        try {
            return Integer.parseInt(string.trim());
        } catch (Exception e) {
            throw new NumberFormatException(e.getMessage());
        }
    }
}
