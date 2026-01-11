package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.PermissionEntity;
import com.nass.infrastructure.repositories.base.IJpaSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends IJpaSpecificationRepository<PermissionEntity, Integer> {
}
