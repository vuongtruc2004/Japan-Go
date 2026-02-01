package com.nass.business.auth.repository;

import com.nass.business.auth.entity.RoleEntity;
import com.nass.common.persistence.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<RoleEntity, Integer> {
}
