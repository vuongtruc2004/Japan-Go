package org.japan.controller.v1.deck;

import lombok.RequiredArgsConstructor;
import org.japan.annotations.ApiResponseFormat;
import org.japan.dto.request.deck.QuizletFormatRequest;
import org.japan.service.deck.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CardController {
    private final CardService cardService;

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping("/quizlet-data-formatting/kanji")
    public ResponseEntity<String> formatQuizletDataKanji(@RequestBody QuizletFormatRequest request) {
        return ResponseEntity.ok(cardService.formatQuizletDataKanji(request));
    }

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping("/quizlet-data-formatting/hiragana")
    public ResponseEntity<String> formatQuizletDataHiragana(@RequestBody QuizletFormatRequest request) {
        return ResponseEntity.ok(cardService.formatQuizletDataHiragana(request));
    }

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping("/quizlet-data-formatting/sentence")
    public ResponseEntity<String> formatQuizletDataSentence(@RequestBody QuizletFormatRequest request) {
        return ResponseEntity.ok(cardService.formatQuizletDataSentence(request));
    }
}
