package com.japan_go_be.features.grammar.helper;

import com.japan_go_be.features.grammar.entity.GrammarEntity;
import com.japan_go_be.features.sentence.entity.SentenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
@RequiredArgsConstructor
public class GrammarHelper {
    public String createBackOfFlashcard(SentenceEntity sentenceEntity) {
        GrammarEntity grammarEntity = sentenceEntity.getGrammarExample().getGrammar();
        StringJoiner stringJoiner = new StringJoiner("\n");

        stringJoiner.add(sentenceEntity.getVietnameseRaw());
        stringJoiner.add("⇒ " + grammarEntity.getGrammarTitle().split("：")[1]);
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
        stringJoiner.add("** Ngữ pháp: ");
        stringJoiner.add(sentenceEntity.getGrammarExample().getGrammar().getGrammarTitle().split("：")[0]);
        stringJoiner.add("** Ví dụ: ");
        stringJoiner.add(sentenceEntity.getJapaneseRaw());
        return stringJoiner.toString();
    }
}
