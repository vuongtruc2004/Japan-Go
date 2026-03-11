package com.japan_go_be.business.helpers.grammar;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import com.japan_go_be.contract.utils.StringUtil;
import com.japan_go_be.infrastructure.entities.grammar.GrammarEntity;
import com.japan_go_be.infrastructure.entities.grammar.SentenceEntity;
import dev.esnault.wanakana.core.Wanakana;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.StringJoiner;

@Component
@RequiredArgsConstructor
public class GrammarHelper {
    private final StringUtil stringUtil;
    private final Tokenizer tokenizer = new Tokenizer();

    public String createBackOfFlashcard(SentenceEntity sentenceEntity) {
        GrammarEntity grammarEntity = sentenceEntity.getGrammarExample().getGrammar();
        StringJoiner stringJoiner = new StringJoiner("\n");

        stringJoiner.add("⇒ " + sentenceEntity.getVietnameseRaw());
        stringJoiner.add("** Ý nghĩa:");
        for (SentenceEntity sentence : grammarEntity.getGrammarMeaning().getSentences()) {
            stringJoiner.add("　・" + sentence.getVietnameseRaw());
        }

        stringJoiner.add("** Chú ý bổ sung:");
        for (SentenceEntity sentence : grammarEntity.getGrammarNote().getSentences()) {
            stringJoiner.add("　・" + sentence.getVietnameseRaw());
        }
        return stringJoiner.toString();
    }

    public String createFrontOfFlashcard(SentenceEntity sentenceEntity) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        String example = sentenceEntity.getJapaneseHtml();
        example = stringUtil.replaceAllSubstringWithHtmlTag(
                example,
                "<span class='grammar-highlight'>",
                " { ",
                "*"
        );

        example = stringUtil.replaceAllSubstringWithHtmlTag(
                example,
                "</span>",
                " } ",
                "*"
        );

        stringJoiner.add(example);
        return stringJoiner.toString();
    }

    public String getGrammarTitleFurigana(String grammarTitle) {
        List<Token> tokens = tokenizer.tokenize(grammarTitle);
        StringBuilder furigana = new StringBuilder();

        for (Token token : tokens) {
            String reading = token.getReading();

            if (reading == null || reading.equals("*")) {
                furigana.append(token.getSurface());
            } else {
                furigana.append(stringUtil.convertKatakanaToHiragana(reading));
            }
        }

        return furigana.toString();
    }

    public String getGrammarTitleRomaji(String grammarTitle) {
        List<Token> tokens = tokenizer.tokenize(grammarTitle);
        StringBuilder romaji = new StringBuilder();

        for (Token token : tokens) {

            String reading = token.getReading();

            if (reading == null || "*".equals(reading)) {
                romaji.append(token.getSurface());
            } else {
                // katakana -> romaji
                String roma = Wanakana.toRomaji(reading);

                // fix particles
                if ("ハ".equals(reading)) roma = "wa";
                if ("ヘ".equals(reading)) roma = "e";
                if ("ヲ".equals(reading)) roma = "o";

                romaji.append(roma);
            }
        }

        return romaji.toString().trim().toLowerCase();
    }
}
