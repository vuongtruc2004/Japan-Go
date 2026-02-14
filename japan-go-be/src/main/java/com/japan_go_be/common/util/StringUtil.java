package com.japan_go_be.common.util;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class StringUtil {
    private final Tokenizer tokenizer = new Tokenizer();

    public String enrichFuriganaToKanjiString(String text) {
        List<Token> tokens = tokenizer.tokenize(text);
        StringBuilder result = new StringBuilder();

        for (Token token : tokens) {
            String surface = token.getSurface();
            String readingKatakana = token.getReading();

            boolean hasReading = readingKatakana != null
                    && !readingKatakana.isBlank()
                    && !"*".equals(readingKatakana);

            // Chỉ furigana cho token có kanji + có reading
            if (!hasReading || !containsKanji(surface)) {
                result.append(surface);
            } else {
                String readingHiragana = convertKatakanaToHiragana(readingKatakana);
                result.append("<ruby><rb>")
                        .append(surface)
                        .append("</rb><rp>(</rp><rt>")
                        .append(readingHiragana)
                        .append("</rt><rp>)</rp></ruby>");
            }
        }

        return result.toString();
    }

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

    private String convertKatakanaToHiragana(String katakana) {
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
