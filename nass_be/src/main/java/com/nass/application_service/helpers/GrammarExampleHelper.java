//package com.nass.application_service.helpers;
//
//import com.nass.infrastructure.entities.common.SentenceEntity;
//import com.nass.infrastructure.entities.grammar.GrammarEntity;
//import com.nass.infrastructure.entities.grammar.GrammarMeaningEntity;
//import com.nass.infrastructure.entities.grammar.GrammarNote;
//import com.nass.infrastructure.entities.grammar.GrammarStructureEntity;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.StringJoiner;
//
//@Component
//@RequiredArgsConstructor
//public class GrammarExampleHelper {
//    private final ReplaceHelper replaceHelper;
//    private final SentenceHelper sentenceHelper;
//
//    public String getGrammarStructure(GrammarEntity grammar) {
//        GrammarStructureEntity structure = grammar.getStructure();
//        StringJoiner joiner = new StringJoiner("<br>");
//        int num = 1;
//        for (SentenceEntity sentence : structure.getSentences()) {
//            String vietnamese = sentenceHelper.enrichFuriganaToKanjiString(sentence.getVietnamese());
//            joiner.add(num + ". " + replaceHelper.replaceAllSubstringWithHtmlTag(vietnamese, "~~", "<s>", "</s>"));
//            num++;
//        }
//        return joiner.toString();
//    }
//
//    public String getGrammarExample(GrammarEntity grammar) {
//        StringJoiner joiner = new StringJoiner("<br/><br/>");
//        int num = 1;
//        for (SentenceEntity sentence : grammar.getExample().getSentences()) {
//            String content = num + ". " + sentenceHelper.enrichFuriganaToKanjiString(sentence.getJapanese()) + "<br>"
//                    + "<div class='example-translation'>" + "<div class='arrow'>⇒</div>" + sentence.getVietnamese() + "</div>";
//            joiner.add(content);
//            num++;
//        }
//        return joiner.toString();
//    }
//
//    public String getGrammarMeaning(GrammarEntity grammar) {
//        GrammarMeaningEntity grammarMeaning = grammar.getGrammarMeaning();
//        return joinSentences(grammarMeaning.getSentences());
//    }
//
//    public String getGrammarAdditionalNote(GrammarEntity grammar) {
//        GrammarNote grammarNote = grammar.getGrammarNote();
//        return joinSentences(grammarNote.getSentences());
//    }
//
//    public String getGrammarLocation(GrammarEntity grammar) {
//        LessonEntity lesson = grammar.getLesson();
//        String lessonNum = lesson.getLessonTitle().split("課")[0];
//        String grammarNum = grammar.getGrammarTitle().split("\\.")[0];
//        return "Bài: " + lessonNum + "<br>" + "Phần: " + grammarNum;
//    }
//
//    private String joinSentences(List<SentenceEntity> sentences) {
//        StringJoiner joiner = new StringJoiner("<br>");
//        int num = 1;
//        for (SentenceEntity sentence : sentences) {
//            joiner.add(num + ". " + sentence.getVietnamese());
//            num++;
//        }
//        return joiner.toString();
//    }
//}
