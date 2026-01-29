package com.nass.api.controllers.v1.kanji;

import com.nass.application_service.dtos.responses.kanji.KanjiResponse;
import com.nass.application_service.services.interfaces.kanji.IKanjiService;
import com.nass.contract.annotations.ApiResponseFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kanji")
public class KanjiController {
    private final IKanjiService kanjiService;
    private final ResourceLoader resourceLoader;

    @Value("${sources.uri}")
    private String sourcesUri;

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<KanjiResponse>> importKanjiFromKanjidic(
            @RequestParam("kanjidic") MultipartFile kanjidicFile,
            @RequestParam("kanji-jlpt") MultipartFile kanjiJlptFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kanjiService.importKanjiFromKanjidic(kanjidicFile, kanjiJlptFile));
    }

    @ApiResponseFormat(devMessage = "", clientMessage = "")
    @GetMapping(value = "/kanji-vg")
    public ResponseEntity<String> getKanjiVgFromKanji(@RequestParam String kanji) throws IOException {
        String file = String.format("%05x.svg", kanji.codePointAt(0));

        Path basePath = Paths.get(URI.create(sourcesUri));
        Path svgPath = basePath.resolve("kanjivg").resolve(file);

        if (!Files.exists(svgPath)) {
            return ResponseEntity.notFound().build();
        }

        String raw = Files.readString(svgPath);
        String svgOnly = extractSvgOnly(raw);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/svg+xml"))
                .body(svgOnly);
    }

    private String extractSvgOnly(String raw) {
        int start = raw.indexOf("<svg");
        int end = raw.lastIndexOf("</svg>");

        if (start == -1 || end == -1) {
            return raw; // fallback nếu format lạ
        }

        return raw.substring(start, end + 6); // +6 vì "</svg>" dài 6 ký tự
    }
}
