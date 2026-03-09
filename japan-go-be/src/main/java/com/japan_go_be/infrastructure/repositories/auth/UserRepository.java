package com.japan_go_be.infrastructure.repositories.auth;

import com.japan_go_be.infrastructure.repositories.base.BaseRepository;
import com.japan_go_be.infrastructure.entities.auth.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {
}
