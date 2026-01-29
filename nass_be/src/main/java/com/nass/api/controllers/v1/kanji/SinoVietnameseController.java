package com.nass.api.controllers.v1.kanji;

import com.nass.application_service.dtos.requests.kanji.GetSinoVietnameseRequest;
import com.nass.application_service.dtos.responses.kanji.KanjiResponse;
import com.nass.application_service.services.implementations.kanji.SinoVietnameseService;
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

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping
    public ResponseEntity<String> getSinoVietnameseOfKanji(@RequestBody GetSinoVietnameseRequest getSinoVietnameseRequest) {
        return ResponseEntity.ok(sinoVietnameseService.getSinoVietnameseOfKanjiList(getSinoVietnameseRequest));
    }

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping(value = "/import/main", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> importMainSinoVietnamese(
            @RequestParam("main-sino-vietnamese") MultipartFile mainSinoVietnameseFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sinoVietnameseService.importMainSinoVietnameseMF(mainSinoVietnameseFile));
    }

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> enrichSinoVietnameseToAllKanji(
            @RequestParam("sino-vietnamese") List<MultipartFile> sinoVietnameseFiles) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sinoVietnameseService.importSinoVietnameseMF(sinoVietnameseFiles));
    }
}
