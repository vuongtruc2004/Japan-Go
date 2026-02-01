package com.nass.business.folder.entity;

import com.nass.business.lesson.entity.LessonEntity;
import com.nass.common.persistence.BaseEntity;
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
public class FolderEntity extends BaseEntity<Long> {

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

