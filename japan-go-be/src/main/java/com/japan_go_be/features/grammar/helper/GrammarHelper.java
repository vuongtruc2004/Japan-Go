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
}
