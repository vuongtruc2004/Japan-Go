package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.RoleEntity;
import com.nass.infrastructure.repositories.base.IJpaSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends IJpaSpecificationRepository<RoleEntity, Integer> {
}
