package com.nass.business.kanji.controller.v1;

import com.nass.common.annotation.ApiResponseFormat;
import com.nass.business.kanji.dto.request.GetSinoVietnameseRequest;
import com.nass.business.kanji.dto.response.KanjiResponse;
import com.nass.business.kanji.service.SinoVietnameseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sino-vietnamese")
public class SinoVietnameseController {
    private final SinoVietnameseService sinoVietnameseService;

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping
    public ResponseEntity<String> getSinoVietnameseOfKanji(@RequestBody GetSinoVietnameseRequest getSinoVietnameseRequest) {
        return ResponseEntity.ok(sinoVietnameseService.getSinoVietnameseOfKanjiList(getSinoVietnameseRequest));
    }

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping(value = "/import/main", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> importMainSinoVietnamese(
            @RequestParam("main-sino-vietnamese") MultipartFile mainSinoVietnameseFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sinoVietnameseService.importMainSinoVietnamese(mainSinoVietnameseFile));
    }

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> enrichSinoVietnameseToAllKanji(
            @RequestParam("sino-vietnamese") List<MultipartFile> sinoVietnameseFiles) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sinoVietnameseService.importSinoVietnamese(sinoVietnameseFiles));
    }
}
