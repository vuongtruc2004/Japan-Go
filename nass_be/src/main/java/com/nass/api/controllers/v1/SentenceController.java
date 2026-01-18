package com.nass.api.controllers.v1;


import com.nass.application_service.services.interfaces.ISentenceService;
import com.nass.contract.annotations.ApiResponseFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sentence")
public class SentenceController {

    private final ISentenceService sentenceService;

    @ApiResponseFormat(devMessage = "", userMessage = "")
    @GetMapping
    public ResponseEntity<String> getKanjiById(@RequestParam(name = "sentence") String sentence) {
        return ResponseEntity.ok(sentenceService.enrichFuriganaToKanjiString(sentence));
    }
}
