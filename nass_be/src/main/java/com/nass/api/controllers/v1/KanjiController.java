package com.nass.api.controllers.v1;

import com.nass.application_service.dto.entries.KanjiEntry;
import com.nass.application_service.services.interfaces.IKanjiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kanji")
public class KanjiController {
    private final IKanjiService kanjiService;

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, KanjiEntry>> enrichSinoVietnameseToKanjiFromBanks(
            @RequestParam("sinovn") List<MultipartFile> sinovnFiles,
            @RequestParam("kanjidic2") MultipartFile kanjidic2File,
            @RequestParam("kanjijlpt") MultipartFile kanjijlptFile) {
        return ResponseEntity.ok(kanjiService.getKanji(sinovnFiles, kanjidic2File, kanjijlptFile));
    }
}
