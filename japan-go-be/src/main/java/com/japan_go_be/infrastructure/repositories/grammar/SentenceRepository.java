package com.japan_go_be.infrastructure.repositories.grammar;

import com.japan_go_be.infrastructure.repositories.base.BaseRepository;
import com.japan_go_be.infrastructure.entities.grammar.SentenceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SentenceRepository extends BaseRepository<SentenceEntity> {
    @Query("""
                select distinct s
                from SentenceEntity s
                join s.grammarExample ge
                join ge.grammar g
                join g.grammarLesson gl
                join gl.lesson l
                join l.folders f
                where f.id = :folderId
            """)
    List<SentenceEntity> findAllGrammarExampleSentencesInFolder(@Param("folderId") Long folderId);
}
