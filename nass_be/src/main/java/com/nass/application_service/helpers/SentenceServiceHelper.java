package com.nass.application_service.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SentenceServiceHelper {

    public boolean containsKanji(String s) {
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

    public String escapeHtml(String s) {
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
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
