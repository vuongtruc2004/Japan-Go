package com.nass.infrastructure.repositories.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IJpaSpecificationRepository<T, TKey> extends JpaRepository<T, TKey>, JpaSpecificationExecutor<T> {
}
