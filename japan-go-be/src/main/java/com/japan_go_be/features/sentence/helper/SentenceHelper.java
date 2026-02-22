package com.japan_go_be.features.sentence.helper;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SentenceHelper {

    private final Tokenizer tokenizer = new Tokenizer();

    /**
     * Enrich Japanese sentence with furigana (ruby),
     * correctly handling okurigana.
     */
    public String enrichFurigana(String text) {
        List<Token> tokens = tokenizer.tokenize(text);
        StringBuilder result = new StringBuilder();

        for (Token token : tokens) {
            String surface = token.getSurface();
            String reading = token.getReading();

            boolean hasReading = reading != null
                    && !reading.isBlank()
                    && !"*".equals(reading);

            // Không có kanji hoặc không có reading → giữ nguyên
            if (!hasReading || !containsKanji(surface)) {
                result.append(surface);
                continue;
            }

            String readingHiragana =
                    katakanaToHiragana(reading);

            OkuriganaSplit split =
                    splitOkurigana(surface, readingHiragana);

            // ✅ Case có okurigana (chuẩn)
            if (split != null) {
                result.append("<ruby><rb>")
                        .append(split.kanjiPart())
                        .append("</rb><rp>(</rp><rt>")
                        .append(split.kanjiReading())
                        .append("</rt><rp>)</rp></ruby>")
                        .append(split.okurigana());
            }
            // ❌ fallback an toàn
            else {
                result.append("<ruby><rb>")
                        .append(surface)
                        .append("</rb><rp>(</rp><rt>")
                        .append(readingHiragana)
                        .append("</rt><rp>)</rp></ruby>");
            }
        }

        return result.toString();
    }

    /* ===================== CORE OKURIGANA LOGIC ===================== */

    private OkuriganaSplit splitOkurigana(
            String surface,
            String readingHiragana
    ) {
        int sIdx = surface.length() - 1;
        int rIdx = readingHiragana.length() - 1;

        // So khớp từ cuối: kana == kana → okurigana
        while (sIdx >= 0 && rIdx >= 0) {
            char sChar = surface.charAt(sIdx);
            char rChar = readingHiragana.charAt(rIdx);

            if (isKana(sChar) && sChar == rChar) {
                sIdx--;
                rIdx--;
            } else {
                break;
            }
        }

        String kanjiPart = surface.substring(0, sIdx + 1);
        String okurigana = surface.substring(sIdx + 1);
        String kanjiReading = readingHiragana.substring(0, rIdx + 1);

        // Safety check
        if (kanjiPart.isBlank() || kanjiReading.isBlank()) {
            return null;
        }

        return new OkuriganaSplit(
                kanjiPart,
                kanjiReading,
                okurigana
        );
    }

    /* ===================== UTIL METHODS ===================== */

    private boolean containsKanji(String text) {
        for (char c : text.toCharArray()) {
            if (c >= '一' && c <= '龯') {
                return true;
            }
        }
        return false;
    }

    private boolean isKana(char c) {
        return (c >= 'ぁ' && c <= 'ん') ||
                (c >= 'ァ' && c <= 'ン');
    }

    private String katakanaToHiragana(String text) {
        StringBuilder sb = new StringBuilder(text.length());
        for (char c : text.toCharArray()) {
            if (c >= 'ァ' && c <= 'ン') {
                sb.append((char) (c - 0x60));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /* ===================== INTERNAL DTO ===================== */

    private record OkuriganaSplit(
            String kanjiPart,
            String kanjiReading,
            String okurigana
    ) {
    }
}