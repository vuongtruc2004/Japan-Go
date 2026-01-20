package com.nass.api.controllers.v1;


import com.nass.application_service.services.interfaces.ISentenceService;
import com.nass.contract.annotations.ApiResponseFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sentence")
public class SentenceController {
    private final ISentenceService sentenceService;

    @ApiResponseFormat(devMessage = "", userMessage = "")
    @GetMapping(value = "/export", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<byte[]> exportAllExampleSentencesToExcel() {
        byte[] bytes = sentenceService.exportAllExampleSentencesToExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename("sentences.xlsx")
                .build());
        headers.setContentLength(bytes.length);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
