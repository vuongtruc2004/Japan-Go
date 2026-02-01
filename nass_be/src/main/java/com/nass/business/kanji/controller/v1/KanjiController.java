package com.nass.business.kanji.controller.v1;

import com.nass.business.kanji.dto.response.KanjiResponse;
import com.nass.business.kanji.service.KanjiService;
import com.nass.common.annotation.ApiResponseFormat;
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
    private final KanjiService kanjiService;


    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> importKanjiFromKanjidic(
            @RequestParam("kanjidic") MultipartFile kanjidicFile,
            @RequestParam("kanji-jlpt") MultipartFile kanjiJlptFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kanjiService.importKanjiFromKanjidic(kanjidicFile, kanjiJlptFile));
    }
}
