package org.japan.persistence.repository.auth;

import org.japan.entity.auth.PermissionEntity;
import org.japan.persistence.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends BaseRepository<PermissionEntity> {
}
