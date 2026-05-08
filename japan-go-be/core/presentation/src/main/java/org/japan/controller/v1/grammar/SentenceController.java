package org.japan.controller.v1.grammar;

import lombok.RequiredArgsConstructor;
import org.japan.annotations.ApiResponseFormat;
import org.japan.constants.ContentTypes;
import org.japan.message.sentence.SentenceMessage;
import org.japan.service.grammar.SentenceService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sentences")
public class SentenceController {
    private final SentenceService sentenceService;

    @ApiResponseFormat(devMessage = SentenceMessage.SENTENCE_EXAMPLE_EXPORTED, clientMessage = SentenceMessage.SENTENCE_EXAMPLE_EXPORTED)
    @GetMapping("/grammar/export/{folderId}")
    public ResponseEntity<byte[]> exportAllGrammarExampleSentencesInFolder(@PathVariable Long folderId) {
        byte[] data = sentenceService.exportAllGrammarExampleSentencesInFolder(folderId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=grammars.xlsx")
                .contentType(MediaType.parseMediaType(ContentTypes.APPLICATION_XLSX))
                .body(data);
    }
}
