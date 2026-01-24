package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.auth.UserEntity;
import com.nass.infrastructure.repositories.base.BaseJpaSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseJpaSpecificationRepository<UserEntity, Integer> {
}
