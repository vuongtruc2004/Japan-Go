package com.japan_go_be.infrastructure.repositories.grammar;

import com.japan_go_be.infrastructure.repositories.base.BaseRepository;
import com.japan_go_be.infrastructure.entities.grammar.GrammarEntity;
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
