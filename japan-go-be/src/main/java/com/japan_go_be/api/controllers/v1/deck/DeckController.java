package com.japan_go_be.api.controllers.v1.deck;

import com.japan_go_be.business.dto.requests.card.DeckRequest;
import com.japan_go_be.business.dto.responses.card.DeckResponse;
import com.japan_go_be.business.services.card.DeckService;
import com.japan_go_be.contract.annotations.ApiResponseFormat;
import com.japan_go_be.contract.message.card.DeckMessage;
import lombok.RequiredArgsConstructor;
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
