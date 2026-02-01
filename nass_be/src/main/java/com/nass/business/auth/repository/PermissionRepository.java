package com.nass.business.auth.repository;

import com.nass.business.auth.entity.PermissionEntity;
import com.nass.common.persistence.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends BaseRepository<PermissionEntity, Integer> {
}
