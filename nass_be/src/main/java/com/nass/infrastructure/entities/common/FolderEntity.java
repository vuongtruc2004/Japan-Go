package com.nass.infrastructure.entities.common;

import com.nass.infrastructure.entities.base.BaseEntity;
import com.nass.infrastructure.entities.lesson.LessonEntity;
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

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    List<LessonEntity> lessons = new ArrayList<>();
}

