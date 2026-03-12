package com.japan_go_be.business.helpers.card;

import com.japan_go_be.infrastructure.entities.card.CardEntity;
import com.japan_go_be.infrastructure.entities.card.DeckEntity;
import com.japan_go_be.infrastructure.entities.grammar.SentenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeckHelper {
    private final CardHelper cardHelper;

    public List<CardEntity> getListCardFromGrammarLesson(DeckEntity deck, List<SentenceEntity> sentences) {
        List<CardEntity> cards = new ArrayList<>();
        int position = 0;
        for (SentenceEntity sentence : sentences) {
            CardEntity card = CardEntity.builder()
                    .deck(deck)
                    .front(sentence.getJapaneseHtml())
                    .grammar(sentence.getGrammarExample().getGrammar())
                    .position(position++)
                    .build();
            cards.add(card);
        }
        return cards;
    }
}
