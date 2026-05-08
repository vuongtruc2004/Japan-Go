package org.japan.service.card;

import lombok.RequiredArgsConstructor;
import org.japan.dto.mapper.DeckDtoMapper;
import org.japan.dto.request.card.DeckRequest;
import org.japan.dto.response.card.DeckResponse;
import org.japan.entity.card.CardEntity;
import org.japan.entity.card.DeckEntity;
import org.japan.entity.grammar.SentenceEntity;
import org.japan.helper.card.DeckHelper;
import org.japan.persistence.repository.card.DeckRepository;
import org.japan.persistence.repository.grammar.SentenceRepository;
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
