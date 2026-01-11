package com.nass.application_service.dto.response;

import com.nass.application_service.dto.response.base.BaseResponse;
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

    List<SinoVietnameseResponse> sinoVietnameseList;

    List<String> onyomiList;

    List<String> kunyomiList;

    List<String> kanjiMeaningList;
}
