package com.nass.application_service.dto.entry;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinoVietnameseMeaningEntry {
    String readingText;
}
