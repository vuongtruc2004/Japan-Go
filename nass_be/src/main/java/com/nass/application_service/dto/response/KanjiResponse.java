package com.nass.application_service.dto.response;

import com.nass.application_service.dto.response.base.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiResponse extends BaseResponse<Integer> {
    String kanji;
    String unicode;
    Integer grade;
    Integer strokeCount;
    Integer frequency;
    Integer jlptLevel;

    List<String> sinoVietnamese;

    List<String> onyomi;

    List<String> kunyomi;

    List<String> meanings;
}
