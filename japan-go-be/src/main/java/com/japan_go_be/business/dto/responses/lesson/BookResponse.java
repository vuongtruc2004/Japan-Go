package com.japan_go_be.business.dto.responses.lesson;

import com.japan_go_be.business.dto.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse extends BaseResponse {
    String vietnameseTitle;
    String japaneseTitle;
    String description;
    String thumbnailUrl;
}
