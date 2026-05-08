package org.japan.repositories.auth;

import org.japan.entity.auth.UserEntity;
import org.japan.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {
}
