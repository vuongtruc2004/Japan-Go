package com.japan_go_be.contract.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class StringUtil {
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

    public String removeAllSubstring(String original, String substring) {
        if (original == null || !original.contains(substring)) {
            return original;
        }
        String regex = Pattern.quote(substring);
        original = original.replaceAll(regex, "");
        return original;
    }

    private boolean containsKanji(String s) {
        for (int i = 0; i < s.length(); i++) {
            Character.UnicodeBlock b = Character.UnicodeBlock.of(s.charAt(i));
            if (b == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || b == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                    || b == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B) {
                return true;
            }
        }
        return false;
    }

    public String convertKatakanaToHiragana(String katakana) {
        if (katakana == null || katakana.isBlank()) return "";
        StringBuilder hiragana = new StringBuilder(katakana.length());
        for (int i = 0; i < katakana.length(); i++) {
            char c = katakana.charAt(i);
            // ァ(0x30A1) .. ヶ(0x30F6) -> ぁ(0x3041) .. ゖ(0x3096)
            if (c >= 0x30A1 && c <= 0x30F6) hiragana.append((char) (c - 0x60));
            else hiragana.append(c);
        }
        return hiragana.toString();
    }
}
