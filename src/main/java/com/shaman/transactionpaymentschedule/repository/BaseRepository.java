package com.shaman.transactionpaymentschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {
    List<T> findAllByRemovedAtIsNull();
    Optional<T> findByUuidAndRemovedAtIsNull(UUID uuid);
    Optional<T> findByIdAndRemovedAtIsNull(Long id);
}
