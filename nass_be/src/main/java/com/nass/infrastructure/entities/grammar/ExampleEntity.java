package com.nass.infrastructure.entities.grammar;

import com.nass.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "example")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExampleEntity extends BaseEntity<Integer> {
    @Builder.Default
    @OneToMany(mappedBy = "example", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SentenceEntity> sentences = new ArrayList<>();

    @OneToOne(mappedBy = "example")
    GrammarEntity grammar;
}
