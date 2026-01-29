package com.nass.application_service.dtos.entries;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiDicEntry {
    String kanjiCharacter;
    String unicode;
    Integer grade;
    Integer strokeCount;
    Integer frequency;
    Integer jlptLevel;
    String mainSinoVietnamese;

    Set<String> sinoVietnameseSet = new HashSet<>();
    Set<String> onyomiSet = new HashSet<>();
    Set<String> kunyomiSet = new HashSet<>();
    Set<String> kanjiMeaningSet = new HashSet<>();
}
