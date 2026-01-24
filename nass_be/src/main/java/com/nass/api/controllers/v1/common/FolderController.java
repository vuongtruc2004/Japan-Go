package com.nass.api.controllers.v1.common;

import com.nass.application_service.dto.requests.common.FolderRequest;
import com.nass.application_service.dto.responses.common.FolderResponse;
import com.nass.application_service.services.interfaces.common.IFolderService;
import com.nass.contract.annotations.ApiResponseFormat;
import com.nass.contract.enums.messages.FolderMessageEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/folder")
public class FolderController {
    private final IFolderService folderService;

    @ApiResponseFormat(devMessage = FolderMessageEnum.KEY_FOLDER_CREATED, clientMessage = FolderMessageEnum.KEY_FOLDER_CREATED)
    @PostMapping
    public ResponseEntity<FolderResponse> createNewFolder(@RequestBody FolderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(folderService.createNewFolder(request));
    }

    @ApiResponseFormat(devMessage = FolderMessageEnum.KEY_FOLDER_GET_ALL, clientMessage = FolderMessageEnum.KEY_FOLDER_GET_ALL)
    @GetMapping("/all")
    public ResponseEntity<List<FolderResponse>> getAllFolders() {
        return ResponseEntity.ok(folderService.getAllFolders());
    }
}
