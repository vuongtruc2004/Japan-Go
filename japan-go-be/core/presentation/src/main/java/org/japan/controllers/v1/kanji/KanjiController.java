package org.japan.controllers.v1.kanji;

import lombok.RequiredArgsConstructor;
import org.japan.annotations.ApiResponseFormat;
import org.japan.dto.response.kanji.KanjiResponse;
import org.japan.message.kanji.KanjiMessage;
import org.japan.services.kanji.KanjiService;
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
    private final KanjiService kanjiService;

    @ApiResponseFormat(
            devMessage = KanjiMessage.GET_ALL_KANJI_BY_JLPT_LEVEL_SUCCESS,
            clientMessage = KanjiMessage.GET_ALL_KANJI_BY_JLPT_LEVEL_SUCCESS
    )
    @GetMapping
    public ResponseEntity<List<KanjiResponse>> getKanjiByJlptLevel(
            @RequestParam("jlpt-level") Integer jlptLevel
    ) {
        return ResponseEntity.ok(kanjiService.getKanjiByJlptLevel(jlptLevel));
    }

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> importKanjiFromKanjidic(
            @RequestParam("kanjidic") MultipartFile kanjidicFile,
            @RequestParam("kanji-jlpt") MultipartFile kanjiJlptFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kanjiService.importKanjiFromKanjidic(kanjidicFile, kanjiJlptFile));
    }
}
