package com.japan_go_be.business.helpers.grammar;

import com.japan_go_be.contract.utils.StringUtil;
import com.japan_go_be.infrastructure.entities.grammar.GrammarEntity;
import com.japan_go_be.infrastructure.entities.grammar.SentenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
@RequiredArgsConstructor
public class GrammarHelper {
    private final StringUtil stringUtil;

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
}
