package com.japan_go_be.features.lesson.repository;

import com.japan_go_be.common.persistence.BaseRepository;
import com.japan_go_be.features.lesson.entity.LessonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                order by coalesce(l.modifiedTime, l.createdTime) desc
            """)
    Page<LessonEntity> findAllOrderByLastModifiedTimeDesc(Pageable pageable);

}
