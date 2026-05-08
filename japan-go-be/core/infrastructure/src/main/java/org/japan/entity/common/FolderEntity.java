package org.japan.entity.common;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.japan.entity.base.BaseEntity;
import org.japan.entity.lesson.LessonEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "folders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderEntity extends BaseEntity {

    @Column(name = "folder_name", nullable = false)
    String folderName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_folder_id")
    FolderEntity parent;

    @Builder.Default
    @Column(name = "is_pinned_to_sidebar")
    Boolean isPinnedToSidebar = false;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    List<FolderEntity> children;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "folder_lesson",
            joinColumns = @JoinColumn(name = "folder_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    List<LessonEntity> lessons = new ArrayList<>();
}

