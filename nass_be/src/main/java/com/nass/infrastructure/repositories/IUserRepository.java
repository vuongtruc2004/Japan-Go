package com.nass.infrastructure.repositories;

import com.nass.infrastructure.entities.UserEntity;
import com.nass.infrastructure.repositories.base.IJpaSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IJpaSpecificationRepository<UserEntity, Integer> {
}
