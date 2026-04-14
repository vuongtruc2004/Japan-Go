package com.japan_go_be.api.controllers.v1.kanji;

import com.japan_go_be.business.dto.responses.kanji.KanjiResponse;
import com.japan_go_be.business.services.kanji.KanjiService;
import com.japan_go_be.contract.annotations.ApiResponseFormat;
import com.japan_go_be.contract.message.kanji.KanjiMessage;
import lombok.RequiredArgsConstructor;
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
