package com.japan_go_be.features.grammar.repository;

import com.japan_go_be.common.persistence.BaseRepository;
import com.japan_go_be.features.grammar.entity.GrammarEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GrammarRepository extends BaseRepository<GrammarEntity> {

    @Query("""
                select distinct g
                from GrammarEntity g
                    join g.grammarLesson gl
                    join gl.lesson l
                    join l.folders f
                where f.id = :folderId
            """)
    List<GrammarEntity> findAllByGrammarsInFolder(@Param("folderId") Long folderId);
}
