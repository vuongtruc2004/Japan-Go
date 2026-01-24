//package com.nass.api.controllers.v1;
//
//import com.nass.application_service.services.GrammarService;
//import com.nass.contract.annotations.ApiResponseFormat;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/grammar")
//public class GrammarController {
//    private final GrammarService grammarService;
//
//    @ApiResponseFormat(devMessage = "", userMessage = "")
//    @GetMapping(value = "/export", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
//    public ResponseEntity<byte[]> exportAllGrammarsToExcel() {
//        byte[] bytes = grammarService.exportAllGrammarsToExcel();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(
//                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
//        headers.setContentDisposition(ContentDisposition.attachment()
//                .filename("lessons.xlsx")
//                .build());
//        headers.setContentLength(bytes.length);
//
//        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
//    }
//}
