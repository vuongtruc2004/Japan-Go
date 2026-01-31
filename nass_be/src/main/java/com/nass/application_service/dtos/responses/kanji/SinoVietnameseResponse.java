package com.nass.application_service.dtos.responses.kanji;

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
