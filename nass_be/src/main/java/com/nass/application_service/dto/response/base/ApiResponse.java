package com.nass.application_service.dto.response.base;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    @Builder.Default
    Integer statusCode = 200;
    String devMessage;
    String userMessage;
    T data;
}
