package org.japan.persistence.repository.auth;

import org.japan.entity.auth.UserEntity;
import org.japan.persistence.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {
}
