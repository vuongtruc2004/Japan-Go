package org.japan.entity.kanji;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.japan.entity.base.BaseEntity;

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
