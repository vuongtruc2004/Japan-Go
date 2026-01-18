package com.nass.api.controllers.v1;

import com.nass.application_service.dto.requests.GetSinoVietnameseRequest;
import com.nass.application_service.dto.responses.kanji.KanjiResponse;
import com.nass.application_service.services.SinoVietnameseService;
import com.nass.contract.annotations.ApiResponseFormat;
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

    @ApiResponseFormat(devMessage = "", userMessage = "")
    @PostMapping
    public ResponseEntity<String> getSinoVietnameseOfKanji(@RequestBody GetSinoVietnameseRequest getSinoVietnameseRequest) {
        return ResponseEntity.ok(sinoVietnameseService.getSinoVietnameseOfKanji(getSinoVietnameseRequest));
    }

    @ApiResponseFormat(devMessage = "", userMessage = "")
    @PostMapping(value = "/import/main", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> importMainSinoVietnamese(
            @RequestParam("main-sino-vietnamese") MultipartFile mainSinoVietnameseFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sinoVietnameseService.importMainSinoVietnamese(mainSinoVietnameseFile));
    }

    @ApiResponseFormat(devMessage = "", userMessage = "")
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> enrichSinoVietnameseToAllKanji(
            @RequestParam("sino-vietnamese") List<MultipartFile> sinoVietnameseFiles) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sinoVietnameseService.importSinoVietnamese(sinoVietnameseFiles));
    }
}
