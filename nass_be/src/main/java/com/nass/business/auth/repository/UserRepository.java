package com.nass.business.auth.repository;

import com.nass.business.auth.entity.UserEntity;
import com.nass.common.persistence.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Integer> {
}
