package org.japan.dto.mapper.base;

import lombok.RequiredArgsConstructor;
import org.japan.dto.response.base.BaseResponse;
import org.japan.entity.base.BaseEntity;
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
