package com.japan_go_be.infrastructure.repositories.lesson;

import com.japan_go_be.infrastructure.entities.lesson.LessonEntity;
import com.japan_go_be.infrastructure.repositories.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LessonRepository extends BaseRepository<LessonEntity> {
    @Query("""
                select count(f) > 0
                from FolderEntity f
                join f.lessons l
                where f.id = :folderId and l.id = :lessonId
            """)
    boolean existsByLessonIdAndFolderId(Long lessonId, Long folderId);

    @Query("""
                select l
                from LessonEntity l
                order by l.lessonName asc, l.createdBy asc
            """)
    Page<LessonEntity> findAllOrderByLastModifiedTimeDesc(Pageable pageable);

    @Modifying
    @Query(value = "DELETE FROM folder_lesson", nativeQuery = true)
    void deleteAllLessonFolderRelations();
}
