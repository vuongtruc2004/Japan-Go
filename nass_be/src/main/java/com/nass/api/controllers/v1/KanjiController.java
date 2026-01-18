package com.nass.api.controllers.v1;

import com.nass.application_service.dto.responses.kanji.KanjiResponse;
import com.nass.application_service.services.interfaces.IKanjiService;
import com.nass.contract.annotations.ApiResponseFormat;
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
    private final IKanjiService kanjiService;

//    @ApiResponseFormat(devMessage = "", userMessage = "")
//    @GetMapping("/excel")
//    public ResponseEntity<byte[]> buildKanjiAndSinoVietnameseExcel() {
//        String filename = "report.xlsx";
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
//                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
//                .body(kanjiService.buildKanjiAndSinoVietnameseExcel());
//    }

    @ApiResponseFormat(devMessage = "", userMessage = "")
    @GetMapping("/{id}")
    public ResponseEntity<KanjiResponse> getKanjiById(@PathVariable Integer id) {
        return ResponseEntity.ok(kanjiService.getKanjiById(id));
    }

//    @ApiResponseFormat(devMessage = "", userMessage = "")
//    @PostMapping("/sino-vn")
//    public ResponseEntity<List<String>> getSinoVietnameseOfKanjiList(@RequestBody GetSinoVnRequest getSinoVnRequest) {
//        return ResponseEntity.ok(kanjiService.getSinoVietnameseOfKanjiList(getSinoVnRequest));
//    }

    @ApiResponseFormat(devMessage = "", userMessage = "")
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> importKanjiFromKanjidic(
            @RequestParam("kanjidic") MultipartFile kanjidicFile,
            @RequestParam("kanjijlpt") MultipartFile kanjijlptFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kanjiService.importKanjiFromKanjidic(kanjidicFile, kanjijlptFile));
    }
}
