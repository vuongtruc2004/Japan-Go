package com.nass.application_service.dto.response;

import com.nass.application_service.dto.response.base.BaseResponse;
import com.nass.contract.enums.VocabularyTypeEnum;

public class VocabularyResponse extends BaseResponse<Long> {
    String kanji;
    String sinoVietnamese;
    String furigana;
    String vietnamese;
    String note;
    VocabularyTypeEnum vocabularyType;
}
