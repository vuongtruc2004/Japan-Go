package com.nass.application_service.dto.responses.grammar;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SentenceResponse {
    String vietnamese;
    String japanese;
    String english;
}
