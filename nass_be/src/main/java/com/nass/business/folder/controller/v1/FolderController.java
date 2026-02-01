package com.nass.business.folder.controller.v1;

import com.nass.business.folder.constant.FolderMessage;
import com.nass.business.folder.dto.request.FolderLessonRequest;
import com.nass.business.folder.dto.request.FolderRequest;
import com.nass.business.folder.dto.response.FolderDetailsResponse;
import com.nass.business.folder.dto.response.FolderResponse;
import com.nass.business.folder.service.FolderService;
import com.nass.common.annotation.ApiResponseFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/folder")
public class FolderController {
    private final FolderService folderService;

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

    @ApiResponseFormat(devMessage = FolderMessage.FOLDER_DELETED, clientMessage = FolderMessage.FOLDER_DELETED)
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteFolder(@PathVariable Long id) {
        return ResponseEntity.ok(folderService.deleteFolder(id));
    }

    @ApiResponseFormat(devMessage = FolderMessage.FOLDER_GET_BY_ID, clientMessage = FolderMessage.FOLDER_GET_BY_ID)
    @GetMapping("/{id}")
    public ResponseEntity<FolderDetailsResponse> getFolderById(@PathVariable Long id) {
        return ResponseEntity.ok(folderService.getFolderById(id));
    }

    @ApiResponseFormat(devMessage = FolderMessage.FOLDER_ADDED_LESSON, clientMessage = FolderMessage.FOLDER_ADDED_LESSON)
    @PostMapping("/lesson")
    public ResponseEntity<FolderResponse> addLessonToFolder(@RequestBody FolderLessonRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(folderService.addLessonToFolder(request));
    }
}
