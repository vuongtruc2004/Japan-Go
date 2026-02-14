package com.japan_go_be.features.kanji.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinoVietnameseResponse {
    String readingText;
    List<String> sinoVietnameseMeaningList;
}
