package com.nass.business.grammar.helper;

import com.nass.business.grammar.entity.GrammarEntity;
import com.nass.business.lesson.entity.GrammarLessonEntity;
import com.nass.business.sentence.entity.SentenceEntity;
import com.nass.common.util.StringUtil;
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

    public String getGrammarLessons(GrammarEntity grammarEntity) {
        StringJoiner joiner = new StringJoiner("<br/>");
        for (GrammarLessonEntity lesson : grammarEntity.getGrammarLessons()) {
            joiner.add(lesson.getGrammarLessonTitle());
        }
        return joiner.toString();
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
