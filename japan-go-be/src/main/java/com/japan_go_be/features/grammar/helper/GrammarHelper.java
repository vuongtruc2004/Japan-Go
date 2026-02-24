package com.japan_go_be.features.grammar.helper;

import com.japan_go_be.features.grammar.entity.GrammarEntity;
import com.japan_go_be.features.sentence.entity.SentenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
@RequiredArgsConstructor
public class GrammarHelper {
    public String combineGrammarMeaningAndGrammarNote(GrammarEntity grammarEntity) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("Ý nghĩa:");
        int index = 1;
        for (SentenceEntity sentence : grammarEntity.getGrammarMeaning().getSentences()) {
            stringJoiner.add(index + ". " + sentence.getVietnameseRaw());
            index++;
        }

        stringJoiner.add("Chú ý bổ sung:");
        index = 1;
        for (SentenceEntity sentence : grammarEntity.getGrammarNote().getSentences()) {
            stringJoiner.add(index + ". " + sentence.getVietnameseRaw());
            index++;
        }
        return stringJoiner.toString();
    }

    public String combineGrammarTitleAndGrammarStructure(GrammarEntity grammarEntity) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add(grammarEntity.getGrammarTitle());
        stringJoiner.add("Cấu trúc:");
        int index = 1;
        for (SentenceEntity sentence : grammarEntity.getGrammarStructure().getSentences()) {
            stringJoiner.add(index + ". " + sentence.getJapaneseRaw());
            index++;
        }
        return stringJoiner.toString();
    }
}
