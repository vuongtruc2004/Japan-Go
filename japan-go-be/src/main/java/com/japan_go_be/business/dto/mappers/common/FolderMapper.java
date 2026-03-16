package com.japan_go_be.business.dto.mappers.common;

import com.japan_go_be.business.dto.responses.common.FolderResponseTest;
import com.japan_go_be.infrastructure.entities.common.FolderEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface FolderMapper {

    @Named("getFolderName")
    static String getFolderName(String folderName) {
        return "folder: " + folderName;
    }

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "folderName", target = "folderName", qualifiedByName = "getFolderName")
    FolderResponseTest mapEntityToResponse(FolderEntity folderEntity);
}
