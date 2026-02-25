package com.japan_go_be.features.sentence.repository;

import com.japan_go_be.common.persistence.BaseRepository;
import com.japan_go_be.features.sentence.entity.SentenceEntity;
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
