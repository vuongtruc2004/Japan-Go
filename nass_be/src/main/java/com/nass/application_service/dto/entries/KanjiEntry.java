package com.nass.application_service.dto.entries;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiEntry {
    String kanji;
    String unicode;
    Integer grade;
    Integer strokeCount;
    Integer frequency;
    Integer jlptLevel;

    List<String> sinoVietnameses = new ArrayList<>();
    List<String> onyomi = new ArrayList<>();
    List<String> kunyomi = new ArrayList<>();
    List<String> meanings = new ArrayList<>();
}
