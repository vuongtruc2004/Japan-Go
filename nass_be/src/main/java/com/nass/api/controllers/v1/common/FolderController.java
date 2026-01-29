package com.nass.api.controllers.v1.common;

import com.nass.application_service.dtos.requests.common.FolderRequest;
import com.nass.application_service.dtos.responses.common.FolderResponse;
import com.nass.application_service.services.interfaces.common.IFolderService;
import com.nass.contract.annotations.ApiResponseFormat;
import com.nass.contract.constants.messages.common.FolderMessage;
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

    @ApiResponseFormat(devMessage = FolderMessage.FOLDER_CREATED, clientMessage = FolderMessage.FOLDER_CREATED)
    @PostMapping
    public ResponseEntity<FolderResponse> createNewFolder(@RequestBody FolderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(folderService.createNewFolder(request));
    }

    @ApiResponseFormat(devMessage = FolderMessage.FOLDER_GET_ALL, clientMessage = FolderMessage.FOLDER_GET_ALL)
    @GetMapping("/all")
    public ResponseEntity<List<FolderResponse>> getAllFolders() {
        return ResponseEntity.ok(folderService.getAllFolders());
    }
}
