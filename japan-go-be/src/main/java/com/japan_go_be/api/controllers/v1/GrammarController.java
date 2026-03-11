package com.japan_go_be.api.controllers.v1;

import com.japan_go_be.business.dto.requests.grammar.GrammarSearchRequest;
import com.japan_go_be.business.dto.responses.base.PageDetailsResponse;
import com.japan_go_be.business.dto.responses.grammar.GrammarResponse;
import com.japan_go_be.business.services.grammar.GrammarService;
import com.japan_go_be.contract.annotations.ApiResponseFormat;
import com.japan_go_be.contract.constants.ContentType;
import com.japan_go_be.contract.constants.message.grammar.GrammarMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/grammars")
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

    @ApiResponseFormat(
            devMessage = GrammarMessage.GRAMMAR_GET_ALL_SUCCESS,
            clientMessage = GrammarMessage.GRAMMAR_GET_ALL_SUCCESS)
    @PostMapping
    public ResponseEntity<PageDetailsResponse<List<GrammarResponse>>> getAllGrammars(
            @RequestBody GrammarSearchRequest searchRequest,
            Pageable pageable
    ) {
        return ResponseEntity.ok(grammarService.getAllGrammars(searchRequest, pageable));
    }
}
