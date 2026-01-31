package com.nass.application_service.dtos.responses.kanji;

import com.nass.application_service.dtos.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiResponse extends BaseResponse<Integer> {
    String kanjiCharacter;
    String unicode;
    Integer grade;
    Integer strokeCount;
    Integer frequency;
    Integer jlptLevel;
    String mainSinoVietnamese;
    List<String> onyomiList;
    List<String> kunyomiList;
}
