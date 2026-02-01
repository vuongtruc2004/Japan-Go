package com.nass.common.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageDetailsResponse<T> {
    Integer currentPage;
    Integer pageSize;
    Integer totalPages;
    Long totalElements;
    T content;
}
