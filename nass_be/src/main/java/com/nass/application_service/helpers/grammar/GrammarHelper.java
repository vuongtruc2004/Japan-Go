package com.nass.application_service.helpers.grammar;

import com.nass.application_service.helpers.common.StringHelper;
import com.nass.infrastructure.entities.common.SentenceEntity;
import com.nass.infrastructure.entities.grammar.GrammarEntity;
import com.nass.infrastructure.entities.lesson.GrammarLessonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.StringJoiner;

@Component
@RequiredArgsConstructor
public class GrammarHelper {

    private final StringHelper stringHelper;

    public String getGrammarMeaning(GrammarEntity grammarEntity) {
        return joinSentences(grammarEntity.getGrammarMeaning().getSentences());
    }

    public String getGrammarStructure(GrammarEntity grammarEntity) {
        StringJoiner joiner = new StringJoiner("<br/>");
        int num = 1;
        for (SentenceEntity sentence : grammarEntity.getGrammarStructure().getSentences()) {
            joiner.add(num + ". " + stringHelper.enrichFuriganaToKanjiString(sentence.getJapaneseRaw()));
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
            joiner.add("<div class='jp'>" + num + ". " + stringHelper.enrichFuriganaToKanjiString(sentence.getJapaneseHtml()) + "</div>");
            joiner.add("<div class='vn'>" + "<span class='arrow'>⇒ </span>" + sentence.getVietnameseRaw() + "</div>");
            num++;
        }
        return joiner.toString();
    }

}
