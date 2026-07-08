package org.japan.mapper.base;

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
                .modifiedTime(baseEntity.getModifiedTime())
                .build();
    }
}
