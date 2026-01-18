package com.nass.application_service.helpers;

import com.nass.application_service.services.SentenceService;
import com.nass.infrastructure.entities.grammar.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.StringJoiner;

@Component
@RequiredArgsConstructor
public class GrammarServiceHelper {
    private final SentenceService sentenceService;

    public String getGrammarStructure(GrammarEntity grammar) {
        StructureEntity structure = grammar.getStructure();
        StringJoiner joiner = new StringJoiner("<br>");
        int num = 1;
        for (SentenceEntity sentence : structure.getSentences()) {
            joiner.add(num + ". " + convertStrikethrough(sentenceService.enrichFuriganaToKanjiString(sentence.getVietnamese())));
            num++;
        }
        return joiner.toString();
    }

    public String getGrammarExample(GrammarEntity grammar) {
        StringJoiner joiner = new StringJoiner("<br>");
        int num = 1;
        for (SentenceEntity sentence : grammar.getExample().getSentences()) {
            joiner.add(num + ". " + sentenceService.enrichFuriganaToKanjiString(sentence.getJapanese()));
            joiner.add("<div class=\"arrow\">⇒</div>" + sentence.getVietnamese());
            num++;
        }
        return joiner.toString();
    }

    public String getGrammarMeaning(GrammarEntity grammar) {
        GrammarMeaningEntity grammarMeaning = grammar.getGrammarMeaning();
        return joinSentences(grammarMeaning.getSentences());
    }

    public String getGrammarAdditionalNote(GrammarEntity grammar) {
        AdditionalNote additionalNote = grammar.getAdditionalNote();
        return joinSentences(additionalNote.getSentences());
    }

    public String getGrammarLocation(GrammarEntity grammar) {
        LessonEntity lesson = grammar.getLesson();
        String lessonNum = lesson.getLessonTitle().split("課")[0];
        String grammarNum = grammar.getGrammarTitle().split("\\.")[0];
        return "Bài: " + lessonNum + "<br>" + "Phần: " + grammarNum;
    }

    private String joinSentences(List<SentenceEntity> sentences) {
        StringJoiner joiner = new StringJoiner("<br>");
        int num = 1;
        for (SentenceEntity sentence : sentences) {
            joiner.add(num + ". " + sentence.getVietnamese());
            num++;
        }
        return joiner.toString();
    }

    private String convertStrikethrough(String text) {
        if (text == null || !text.contains("~~")) {
            return text;
        }
        while (text.contains("~~")) {
            text = text.replaceFirst("~~", "<s>");
            text = text.replaceFirst("~~", "</s>");
        }
        return text;
    }

}
