package org.japan.persistence.repository.grammar;

import org.japan.entity.grammar.GrammarEntity;
import org.japan.persistence.repository.base.BaseRepository;
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
