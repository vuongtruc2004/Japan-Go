package org.japan.dto.response.kanji;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.japan.dto.response.base.BaseResponse;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KanjiResponse extends BaseResponse {
    String kanjiCharacter;
    String unicode;
    String kanjiVg;
    Integer grade;
    Integer strokeCount;
    Integer frequency;
    Integer jlptLevel;
    String mainSinoVietnamese;
    List<String> onyomiList;
    List<String> kunyomiList;
    List<SinoVietnameseResponse> sinoVietnameseList;
}
