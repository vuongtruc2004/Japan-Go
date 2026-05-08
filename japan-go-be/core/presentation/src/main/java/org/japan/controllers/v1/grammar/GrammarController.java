package org.japan.controllers.v1.grammar;

import lombok.RequiredArgsConstructor;
import org.japan.annotations.ApiResponseFormat;
import org.japan.dto.request.grammar.GrammarSearchRequest;
import org.japan.dto.response.base.PageDetailsResponse;
import org.japan.dto.response.grammar.GrammarResponse;
import org.japan.message.grammar.GrammarMessage;
import org.japan.services.grammar.GrammarService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/grammars")
public class GrammarController {

    private final GrammarService grammarService;

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

    @ApiResponseFormat(
            devMessage = GrammarMessage.GRAMMAR_GET_BY_ID_SUCCESS,
            clientMessage = GrammarMessage.GRAMMAR_GET_BY_ID_SUCCESS
    )
    @GetMapping("/{id}")
    public ResponseEntity<GrammarResponse> getGrammarDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(grammarService.getGrammarDetailsById(id));
    }
}
