//package com.nass.api.controllers.v1;
//
//
//import com.nass.application_service.dto.responses.grammar.LessonResponse;
//import com.nass.application_service.services.LessonService;
//import com.nass.contract.annotations.ApiResponseFormat;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/lesson")
//public class LessonController {
//    private final LessonService lessonService;
//
//    @ApiResponseFormat(devMessage = "", userMessage = "")
//    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<List<LessonResponse>> importLessonsFromNotion(@RequestParam("files") List<MultipartFile> files) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.importLessonsFromNotion(files));
//    }
//}
