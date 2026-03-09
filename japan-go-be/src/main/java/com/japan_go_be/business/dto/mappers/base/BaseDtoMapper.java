package com.japan_go_be.business.dto.mappers.base;

import com.japan_go_be.business.dto.responses.base.BaseResponse;
import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseDtoMapper {
    public BaseResponse baseEntityToBaseResponse(BaseEntity baseEntity) {
        return BaseResponse.builder()
                .id(baseEntity.getId())
                .createdTime(baseEntity.getCreatedTime())
                .createdBy(baseEntity.getCreatedBy())
                .modifiedTime(baseEntity.getModifiedTime())
                .modifiedBy(baseEntity.getModifiedBy())
                .build();
    }
}
