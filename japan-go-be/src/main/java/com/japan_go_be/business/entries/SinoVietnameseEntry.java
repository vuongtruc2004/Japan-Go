package com.japan_go_be.business.entries;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinoVietnameseEntry {
    String readingText;

    @Builder.Default
    List<SinoVietnameseMeaningEntry> sinoVietnameseMeaningList = new ArrayList<>();
}
