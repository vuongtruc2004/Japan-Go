package org.japan.persistence.repository.auth;

import org.japan.entity.auth.RoleEntity;
import org.japan.persistence.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<RoleEntity> {
}
