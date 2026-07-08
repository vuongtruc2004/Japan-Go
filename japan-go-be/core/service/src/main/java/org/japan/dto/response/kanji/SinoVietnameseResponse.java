package org.japan.dto.response.kanji;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.japan.dto.response.base.BaseResponse;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinoVietnameseResponse extends BaseResponse {
    String readingText;

    @Builder.Default
    List<String> sinoVietnameseMeaningList = new ArrayList<>();
}
