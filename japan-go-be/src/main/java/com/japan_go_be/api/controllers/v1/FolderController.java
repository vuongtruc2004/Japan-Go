package com.japan_go_be.api.controllers.v1;

import com.japan_go_be.business.dto.requests.common.FolderLessonRequest;
import com.japan_go_be.business.dto.requests.common.FolderRequest;
import com.japan_go_be.business.dto.responses.base.ApiResponse;
import com.japan_go_be.business.dto.responses.common.FolderResponse;
import com.japan_go_be.business.dto.responses.common.FolderResponseTest;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.business.services.common.FolderService;
import com.japan_go_be.business.services.common.FolderTest;
import com.japan_go_be.contract.annotations.ApiResponseFormat;
import com.japan_go_be.contract.constants.message.common.FolderMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/folders")
public class FolderController {
    private final FolderService folderService;
    private final I18nService i18nService;
    private final FolderTest folderTest;

    @ApiResponseFormat(devMessage = FolderMessage.FOLDER_CREATED, clientMessage = FolderMessage.FOLDER_CREATED)
    @PostMapping
    public ResponseEntity<FolderResponse> createNewFolder(@RequestBody FolderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(folderService.createNewFolder(request));
    }

    @ApiResponseFormat(devMessage = FolderMessage.FOLDER_GET_ALL, clientMessage = FolderMessage.FOLDER_GET_ALL)
    @GetMapping("/all")
    public ResponseEntity<List<FolderResponseTest>> getAllFolders() {
        return ResponseEntity.ok(folderTest.getAllFolders());
    }

    @ApiResponseFormat(devMessage = FolderMessage.FOLDER_DELETED, clientMessage = FolderMessage.FOLDER_DELETED)
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteFolder(@PathVariable Long id) {
        return ResponseEntity.ok(folderService.deleteFolder(id));
    }

    @ApiResponseFormat(devMessage = FolderMessage.FOLDER_GET_BY_ID, clientMessage = FolderMessage.FOLDER_GET_BY_ID)
    @GetMapping("/{id}")
    public ResponseEntity<FolderResponse> getFolderById(@PathVariable Long id) {
        return ResponseEntity.ok(folderService.getFolderById(id));
    }

    @ApiResponseFormat(devMessage = FolderMessage.FOLDER_ADDED_LESSON, clientMessage = FolderMessage.FOLDER_ADDED_LESSON)
    @PostMapping("/lesson")
    public ResponseEntity<Void> addLessonToFolder(@RequestBody FolderLessonRequest request) {
        folderService.addLessonToFolder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiResponseFormat(devMessage = FolderMessage.FOLDER_REMOVED_LESSON, clientMessage = FolderMessage.FOLDER_REMOVED_LESSON)
    @DeleteMapping("/lesson")
    public ResponseEntity<Void> removeLessonFromFolder(@RequestBody FolderLessonRequest request) {
        folderService.removeLessonFromFolder(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/pin-and-unpin/{folderId}")
    public ResponseEntity<ApiResponse<FolderResponse>> pinAndUnpinFolderFromSidebar(@PathVariable Long folderId) {
        FolderResponse folderResponse = folderService.pinAndUnpinFolderFromSidebar(folderId);
        ApiResponse<FolderResponse> apiResponse = ApiResponse.<FolderResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .data(folderResponse)
                .build();

        if (Boolean.TRUE.equals(folderResponse.getIsPinnedToSidebar())) {
            apiResponse.setDevMessage(i18nService.translation(FolderMessage.FOLDER_PIN_SUCCESS));
            apiResponse.setClientMessage(i18nService.translation(FolderMessage.FOLDER_PIN_SUCCESS));
        } else {
            apiResponse.setDevMessage(i18nService.translation(FolderMessage.FOLDER_UNPIN_SUCCESS));
            apiResponse.setClientMessage(i18nService.translation(FolderMessage.FOLDER_UNPIN_SUCCESS));
        }
        return ResponseEntity.ok(apiResponse);
    }

    @ApiResponseFormat(devMessage = FolderMessage.FOLDER_GET_ALL_PIN, clientMessage = FolderMessage.FOLDER_GET_ALL_PIN)
    @GetMapping("/pin")
    public ResponseEntity<List<FolderResponse>> getAllPinFolders() {
        return ResponseEntity.ok(folderService.getAllPinFolders());
    }
}
