package org.japan.dto.response.kanji;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.japan.dto.response.base.BaseResponse;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinoVietnameseResponse extends BaseResponse {
    String readingText;
    List<String> sinoVietnameseMeaningList;
}
