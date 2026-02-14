package com.japan_go_be.features.folder.entity;

import com.japan_go_be.common.persistence.BaseEntity;
import com.japan_go_be.features.lesson.entity.LessonEntity;
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
@Table(name = "folder")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderEntity extends BaseEntity {

    @Column(name = "folder_name", nullable = false)
    String folderName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_folder_id")
    FolderEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    List<FolderEntity> children;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "folder_lesson",
            joinColumns = @JoinColumn(name = "folder_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    List<LessonEntity> lessons = new ArrayList<>();
}

