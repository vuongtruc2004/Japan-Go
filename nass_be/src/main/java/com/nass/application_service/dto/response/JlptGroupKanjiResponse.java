package com.nass.application_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JlptGroupKanjiResponse {
    Integer jlptLevel;
    List<KanjiResponse> kanji;
}
