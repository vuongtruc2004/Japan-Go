package com.japan_go_be.api.controllers.v1.common;

import com.japan_go_be.business.services.common.PowerPointService;
import com.japan_go_be.contract.annotations.ApiResponseFormat;
import com.japan_go_be.contract.constants.ContentType;
import com.japan_go_be.contract.constants.message.FileMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/power-point")
public class PowerPointController {
    private final PowerPointService powerPointService;

    @ApiResponseFormat(devMessage = FileMessage.FILE_MERGE_SUCCESS, clientMessage = FileMessage.FILE_MERGE_SUCCESS)
    @PostMapping(value = "/merge", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ByteArrayResource> mergePowerPointList(
            @RequestParam("files") List<MultipartFile> files
    ) {
        byte[] merge = powerPointService.mergePowerPointList(files);
        ByteArrayResource resource = new ByteArrayResource(merge);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=merged.pptx")
                .contentType(MediaType.parseMediaType(ContentType.APPLICATION_PPTX))
                .contentLength(merge.length)
                .body(resource);
    }
}
