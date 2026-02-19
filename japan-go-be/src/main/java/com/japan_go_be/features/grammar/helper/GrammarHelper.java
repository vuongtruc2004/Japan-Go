package com.japan_go_be.features.grammar.helper;

import com.japan_go_be.common.util.StringUtil;
import com.japan_go_be.features.grammar.entity.GrammarEntity;
import com.japan_go_be.features.sentence.entity.SentenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.StringJoiner;

@Component
@RequiredArgsConstructor
public class GrammarHelper {

    private final StringUtil stringUtil;

    public String getGrammarMeaning(GrammarEntity grammarEntity) {
        return joinSentences(grammarEntity.getGrammarMeaning().getSentences());
    }

    public String getGrammarStructure(GrammarEntity grammarEntity) {
        StringJoiner joiner = new StringJoiner("<br/>");
        int num = 1;
        for (SentenceEntity sentence : grammarEntity.getGrammarStructure().getSentences()) {
            joiner.add(num + ". " + stringUtil.enrichFuriganaToKanjiString(sentence.getJapaneseRaw()));
            num++;
        }
        return joiner.toString();
    }

    public String getGrammarExample(GrammarEntity grammarEntity) {
        return joinSentences(grammarEntity.getGrammarExample().getSentences());
    }

    public String getGrammarNote(GrammarEntity grammarEntity) {
        return joinSentences(grammarEntity.getGrammarNote().getSentences());
    }

    private String joinSentences(List<SentenceEntity> sentences) {
        StringJoiner joiner = new StringJoiner("<br/>");
        int num = 1;
        for (SentenceEntity sentence : sentences) {
            joiner.add("<div class='jp'>" + num + ". " + stringUtil.enrichFuriganaToKanjiString(sentence.getJapaneseHtml()) + "</div>");
            joiner.add("<div class='vn'>" + "<span class='arrow'>⇒ </span>" + sentence.getVietnameseRaw() + "</div>");
            num++;
        }
        return joiner.toString();
    }

}
