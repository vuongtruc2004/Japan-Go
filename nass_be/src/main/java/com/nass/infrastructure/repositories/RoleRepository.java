package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.auth.RoleEntity;
import com.nass.infrastructure.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<RoleEntity, Integer> {
}
