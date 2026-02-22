package com.japan_go_be.features.sentence.controller.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sentence")
public class SentenceController {
//    private final ISentenceService sentenceService;
//
//    @ApiResponseFormat(devMessage = SentenceMessage.SENTENCE_EXAMPLE_EXPORTED, clientMessage = SentenceMessage.SENTENCE_EXAMPLE_EXPORTED)
//    @GetMapping(value = "/export", produces = ContentType.APPLICATION_XLSX)
//    public ResponseEntity<byte[]> exportAllExampleSentencesToExcel() {
//        byte[] bytes = sentenceService.exportAllExampleSentencesToExcel();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(ContentType.APPLICATION_XLSX));
//        headers.setContentDisposition(ContentDisposition.attachment()
//                .filename("sentences.xlsx")
//                .build());
//        headers.setContentLength(bytes.length);
//
//        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
//    }
}
