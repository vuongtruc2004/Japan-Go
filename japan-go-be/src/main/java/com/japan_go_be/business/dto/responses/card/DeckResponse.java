package com.japan_go_be.business.dto.responses.card;

import com.japan_go_be.business.dto.responses.base.BaseResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeckResponse extends BaseResponse {
    String title;

    String description;
}
