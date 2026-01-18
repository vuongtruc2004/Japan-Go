package com.nass.application_service.services;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.helpers.SentenceServiceHelper;
import com.nass.application_service.services.interfaces.ISentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SentenceService implements ISentenceService {
    private final SentenceServiceHelper sentenceServiceHelper;
    private final Tokenizer tokenizer = new Tokenizer();

    @Override
    public String enrichFuriganaToKanjiString(String text) {
        if (text == null || text.isBlank()) {
            throw new FileNotValidException("Text is not valid");
        }

        List<Token> tokens = tokenizer.tokenize(text);
        StringBuilder result = new StringBuilder();

        for (Token token : tokens) {
            String surface = token.getSurface();
            String readingKatakana = token.getReading();

            boolean hasReading = readingKatakana != null
                    && !readingKatakana.isBlank()
                    && !"*".equals(readingKatakana);

            // Chỉ furigana cho token có kanji + có reading
            if (!hasReading || !sentenceServiceHelper.containsKanji(surface)) {
                result.append(surface);
            } else {
                String readingHiragana = sentenceServiceHelper.convertKatakanaToHiragana(readingKatakana);
                result.append("<ruby><rb>")
                        .append(surface)
                        .append("</rb><rp>(</rp><rt>")
                        .append(readingHiragana)
                        .append("</rt><rp>)</rp></ruby>");
            }
        }

        return result.toString();
    }

}
