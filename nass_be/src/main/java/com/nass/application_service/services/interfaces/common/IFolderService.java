package com.nass.application_service.services.interfaces.common;

import com.nass.application_service.dtos.requests.common.FolderRequest;
import com.nass.application_service.dtos.responses.common.FolderResponse;

import java.util.List;

public interface IFolderService {
    FolderResponse createNewFolder(FolderRequest request);

    List<FolderResponse> getAllFolders();
}
