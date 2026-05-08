package org.japan.controllers.v1.deck;

import lombok.RequiredArgsConstructor;
import org.japan.annotations.ApiResponseFormat;
import org.japan.dto.request.card.DeckRequest;
import org.japan.dto.response.card.DeckResponse;
import org.japan.message.card.DeckMessage;
import org.japan.services.card.DeckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/decks")
public class DeckController {
    private final DeckService deckService;

    @ApiResponseFormat(devMessage = DeckMessage.DECK_CREATED_SUCCESS, clientMessage = DeckMessage.DECK_CREATED_SUCCESS)
    @PostMapping
    public ResponseEntity<DeckResponse> createDeckFromFolder(@RequestBody DeckRequest deckRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deckService.createDeckFromFolder(deckRequest));
    }
}
