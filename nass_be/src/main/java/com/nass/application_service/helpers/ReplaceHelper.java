package com.nass.application_service.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class ReplaceHelper {
    public String replaceAllSubstringWithHtmlTag(String original, String substring, String openTag, String closeTag) {
        if (original == null || !original.contains(substring)) {
            return original;
        }

        String regex = Pattern.quote(substring);

        while (original.contains(substring)) {
            original = original.replaceFirst(regex, openTag);
            original = original.replaceFirst(regex, closeTag);
        }
        return original;
    }

}
