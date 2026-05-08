package org.japan.controller.v1.kanji;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.japan.annotations.ApiResponseFormat;
import org.japan.dto.request.kanji.CreateSinoVietnameseRequest;
import org.japan.dto.request.kanji.GetSinoVietnameseRequest;
import org.japan.dto.response.kanji.KanjiResponse;
import org.japan.message.kanji.SinoVietnameseMessage;
import org.japan.service.kanji.SinoVietnameseService;
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
    @PostMapping("/kanji-list")
    public ResponseEntity<String> getSinoVietnameseOfKanjiList(@RequestBody GetSinoVietnameseRequest getSinoVietnameseRequest) {
        return ResponseEntity.ok(sinoVietnameseService.getSinoVietnameseOfKanjiList(getSinoVietnameseRequest));
    }

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping(value = "/import/main", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> importMainSinoVietnamese(
            @RequestParam("main-sino-vietnamese") MultipartFile mainSinoVietnameseFile) {
        sinoVietnameseService.importMainSinoVietnamese(mainSinoVietnameseFile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> enrichSinoVietnameseToAllKanji(
            @RequestParam("sino-vietnamese") List<MultipartFile> sinoVietnameseFiles) {
        sinoVietnameseService.importSinoVietnamese(sinoVietnameseFiles);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiResponseFormat(
            devMessage = SinoVietnameseMessage.SINO_VIETNAMESE_CREATED_SUCCESS,
            clientMessage = SinoVietnameseMessage.SINO_VIETNAMESE_CREATED_SUCCESS
    )
    @PostMapping
    public ResponseEntity<Void> createSinoVietnamese(@RequestBody CreateSinoVietnameseRequest request) {
        sinoVietnameseService.createSinoVietnamese(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiResponseFormat(
            devMessage = SinoVietnameseMessage.SINO_VIETNAMESE_DELETED_SUCCESS,
            clientMessage = SinoVietnameseMessage.SINO_VIETNAMESE_DELETED_SUCCESS
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSinoVietnamese(@PathVariable Long id) {
        sinoVietnameseService.deleteSinoVietnamese(id);
        return ResponseEntity.ok().build();
    }
}
