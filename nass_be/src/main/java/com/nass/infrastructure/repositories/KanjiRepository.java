package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.repositories.base.IJpaSpecificationRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface KanjiRepository extends IJpaSpecificationRepository<KanjiEntity, Integer> {
    Optional<KanjiEntity> findByUnicode(String unicode);

    boolean existsByUnicode(String unicode);

    List<KanjiEntity> findAllByJlptLevel(Integer jlptLevel);

    List<KanjiEntity> findAllByUnicodeIn(Collection<String> unicodes);
}
