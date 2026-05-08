package org.japan.dto.response.lesson;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.japan.dto.response.base.BaseResponse;

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
