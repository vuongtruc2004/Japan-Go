package com.nass.api.controllers.v1.kanji;

import com.nass.application_service.dtos.responses.kanji.KanjiResponse;
import com.nass.application_service.services.interfaces.kanji.IKanjiService;
import com.nass.contract.annotations.ApiResponseFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kanji")
public class KanjiController {
    private final IKanjiService kanjiService;
    private final ResourceLoader resourceLoader;

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> importKanjiFromKanjidic(
            @RequestParam("kanjidic") MultipartFile kanjidicFile,
            @RequestParam("kanji-jlpt") MultipartFile kanjiJlptFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kanjiService.importKanjiFromKanjidic(kanjidicFile, kanjiJlptFile));
    }

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @GetMapping(value = "/kanji-vg")
    public ResponseEntity<Resource> getKanjiVgFromKanji(@RequestParam String kanji) {
        String file = String.format("%05x.svg", kanji.codePointAt(0));
        return ResponseEntity.status(302)
                .header("Location", "/sources/kanjivg/" + file)
                .build();
    }
}
