package com.nass.api.controllers.v1;

import com.nass.application_service.dto.responses.kanji.KanjiResponse;
import com.nass.application_service.services.interfaces.kanji.IKanjiService;
import com.nass.contract.annotations.ApiResponseFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kanji")
public class KanjiController {
    private final IKanjiService kanjiService;

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> importKanjiFromKanjidic(
            @RequestParam("kanjidic") MultipartFile kanjidicFile,
            @RequestParam("kanjijlpt") MultipartFile kanjijlptFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kanjiService.importKanjiFromKanjidic(kanjidicFile, kanjijlptFile));
    }
}
