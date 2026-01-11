package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.kanji.SinoVietnameseEntity;
import com.nass.infrastructure.repositories.base.IJpaSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinoVietnameseRepository extends IJpaSpecificationRepository<SinoVietnameseEntity, Long> {
}
