package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.auth.PermissionEntity;
import com.nass.infrastructure.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends BaseRepository<PermissionEntity, Integer> {
}
