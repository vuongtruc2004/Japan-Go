package com.japan_go_be.infrastructure.entities.kanji;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sino_vietnamese_meaning")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SinoVietnameseMeaningEntity extends BaseEntity {
    @Column(name = "reading_text", nullable = false)
    String readingText;

    @ManyToOne
    @JoinColumn(name = "sino_vietnamese_id")
    SinoVietnameseEntity sinoVietnamese;
}
