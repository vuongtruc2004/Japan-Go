package com.nass.business.lesson.repository;

import com.nass.business.lesson.entity.LessonEntity;
import com.nass.common.persistence.BaseRepository;
import org.springframework.data.jpa.repository.Query;

public interface LessonRepository extends BaseRepository<LessonEntity, Integer> {
    @Query("""
                select count(f) > 0
                from FolderEntity f
                join f.lessons l
                where f.id = :folderId and l.id = :lessonId
            """)
    boolean existsByLessonIdAndFolderId(Integer lessonId, Long folderId);
}
