package com.japan_go_be.features.auth.repository;

import com.japan_go_be.common.persistence.BaseRepository;
import com.japan_go_be.features.auth.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {
}
