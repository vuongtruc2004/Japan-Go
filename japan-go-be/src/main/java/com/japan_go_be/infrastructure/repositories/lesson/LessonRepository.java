package com.japan_go_be.infrastructure.repositories.lesson;

import com.japan_go_be.infrastructure.entities.lesson.LessonEntity;
import com.japan_go_be.infrastructure.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LessonRepository extends BaseRepository<LessonEntity> {
    @Query("""
                select count(f) > 0
                from FolderEntity f
                join f.lessons l
                where f.id = :folderId and l.id = :lessonId
            """)
    boolean existsByLessonIdAndFolderId(Long lessonId, Long folderId);

    @Modifying
    @Query(value = "DELETE FROM folder_lesson", nativeQuery = true)
    void deleteAllLessonFolderRelations();

    @Query("""
            SELECT l from LessonEntity l
            join l.grammarLesson gl
            join gl.grammars g
            where g.id = :grammarId
            """)
    Optional<LessonEntity> findByGrammarId(@Param("grammarId") Long grammarId);
}
