package org.japan.controller.v1.kanji;

import lombok.RequiredArgsConstructor;
import org.japan.annotations.ApiResponseFormat;
import org.japan.dto.request.kanji.UpdateKanjiMainSinoVietnameseRequest;
import org.japan.dto.response.kanji.KanjiResponse;
import org.japan.message.kanji.KanjiMessage;
import org.japan.service.kanji.KanjiService;
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
    @GetMapping("/level")
    public ResponseEntity<List<KanjiResponse>> getKanjiByJlptLevel(
            @RequestParam("jlpt-level") Integer jlptLevel
    ) {
        return ResponseEntity.ok(kanjiService.getKanjiByJlptLevel(jlptLevel));
    }

    @ApiResponseFormat(
            devMessage = KanjiMessage.UPDATE_KANJI_SINO_VIETNAMESE_SUCCESS,
            clientMessage = KanjiMessage.UPDATE_KANJI_SINO_VIETNAMESE_SUCCESS
    )
    @PatchMapping("/main-sino-vietnamese")
    public ResponseEntity<KanjiResponse> updateKanjiMainSinoVietnamese(
            @RequestBody UpdateKanjiMainSinoVietnameseRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kanjiService.updateKanjiMainSinoVietnamese(request));
    }

    @ApiResponseFormat(
            devMessage = KanjiMessage.GET_KANJI_BY_KANJI_CHARACTER_SUCCESS,
            clientMessage = KanjiMessage.GET_KANJI_BY_KANJI_CHARACTER_SUCCESS
    )
    @GetMapping("/character")
    public ResponseEntity<KanjiResponse> getKanjiByKanjiCharacter(
            @RequestParam("kanji-character") String kanjiCharacter
    ) {
        return ResponseEntity.ok(kanjiService.getKanjiByKanjiCharacter(kanjiCharacter));
    }

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> importKanjiFromKanjidic(
            @RequestParam("kanjidic") MultipartFile kanjidicFile,
            @RequestParam("kanji-jlpt") MultipartFile kanjiJlptFile) {
        kanjiService.importKanjiFromKanjidic(kanjidicFile, kanjiJlptFile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
