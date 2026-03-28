package com.japan_go_be.infrastructure.entities.lesson;

import com.japan_go_be.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookEntity extends BaseEntity {
    @Column(name = "vietnamese_title")
    String vietnameseTitle;

    @Column(name = "japanese_title")
    String japaneseTitle;

    String description;
    
    String thumbnailUrl;

    @Builder.Default
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    List<LessonEntity> lessons = new ArrayList<>();
}
