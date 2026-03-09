package com.japan_go_be.api.controllers.v1;

import com.japan_go_be.business.services.grammar.SentenceService;
import com.japan_go_be.contract.annotations.ApiResponseFormat;
import com.japan_go_be.contract.constants.ContentType;
import com.japan_go_be.contract.constants.message.sentence.SentenceMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sentence")
public class SentenceController {
    private final SentenceService sentenceService;

    @ApiResponseFormat(devMessage = SentenceMessage.SENTENCE_EXAMPLE_EXPORTED, clientMessage = SentenceMessage.SENTENCE_EXAMPLE_EXPORTED)
    @GetMapping("/grammar/export/{folderId}")
    public ResponseEntity<byte[]> exportAllGrammarExampleSentencesInFolder(@PathVariable Long folderId) {
        byte[] data = sentenceService.exportAllGrammarExampleSentencesInFolder(folderId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=grammars.xlsx")
                .contentType(MediaType.parseMediaType(ContentType.APPLICATION_XLSX))
                .body(data);
    }
}
