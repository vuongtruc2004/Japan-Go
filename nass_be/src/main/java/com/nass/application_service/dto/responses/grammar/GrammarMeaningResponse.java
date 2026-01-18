package com.nass.application_service.dto.responses.grammar;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GrammarMeaningResponse {
    String vietnameseTranslation;

    List<SentenceResponse> sentences;
}
