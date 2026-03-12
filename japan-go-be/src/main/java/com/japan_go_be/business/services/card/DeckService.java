package com.japan_go_be.business.services.card;

import com.japan_go_be.business.dto.mappers.DeckDtoMapper;
import com.japan_go_be.business.dto.requests.card.DeckRequest;
import com.japan_go_be.business.dto.responses.card.DeckResponse;
import com.japan_go_be.business.helpers.card.DeckHelper;
import com.japan_go_be.infrastructure.entities.card.CardEntity;
import com.japan_go_be.infrastructure.entities.card.DeckEntity;
import com.japan_go_be.infrastructure.entities.grammar.SentenceEntity;
import com.japan_go_be.infrastructure.repositories.card.DeckRepository;
import com.japan_go_be.infrastructure.repositories.grammar.SentenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeckService {
    private final DeckRepository deckRepository;
    private final DeckDtoMapper deckDtoMapper;
    private final SentenceRepository sentenceRepository;
    private final DeckHelper deckHelper;

    @Transactional
    public DeckResponse createDeckFromFolder(DeckRequest request) {
        DeckEntity deck = DeckEntity.builder()
                .title(request.title())
                .description(request.description())
                .build();

        List<SentenceEntity> sentences = sentenceRepository.findAllGrammarExampleSentencesInFolder(request.folderId());
        List<CardEntity> cards = deckHelper.getListCardFromGrammarLesson(deck, sentences);
        deck.getCards().addAll(cards);

        DeckEntity savedDeck = deckRepository.save(deck);
        return deckDtoMapper.deckEntityToDeckResponse(savedDeck);
    }
}
