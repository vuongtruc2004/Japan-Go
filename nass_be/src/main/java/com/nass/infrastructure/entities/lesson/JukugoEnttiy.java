package com.nass.infrastructure.entities.lesson;

import com.nass.infrastructure.entities.base.BaseEntity;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jukugo")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JukugoEnttiy extends BaseEntity<Long> {
    @Column(nullable = false, unique = true)
    String jukugo;

    @ManyToOne
    @JoinColumn(name = "main_kanji_id")
    KanjiEntity mainKanji;
}
