package com.japan_go_be.features.grammar.controller.v1;

import com.japan_go_be.common.annotation.ApiResponseFormat;
import com.japan_go_be.common.constant.ContentType;
import com.japan_go_be.features.grammar.constant.messages.GrammarMessage;
import com.japan_go_be.features.grammar.service.GrammarService;
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
@RequestMapping("/api/v1/grammar")
public class GrammarController {

    private final GrammarService grammarService;

    @ApiResponseFormat(devMessage = GrammarMessage.GRAMMAR_EXPORTED, clientMessage = GrammarMessage.GRAMMAR_EXPORTED)
    @GetMapping("/export/{folderId}")
    public ResponseEntity<byte[]> exportAllGrammarsInFolder(@PathVariable Long folderId) {
        byte[] data = grammarService.exportAllGrammarsInFolder(folderId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=grammars.xlsx")
                .contentType(MediaType.parseMediaType(ContentType.APPLICATION_XLSX))
                .body(data);
    }
}
