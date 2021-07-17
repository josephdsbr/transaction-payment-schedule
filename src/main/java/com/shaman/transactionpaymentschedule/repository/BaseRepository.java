package com.shaman.transactionpaymentschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {
    List<T> findAllByRemovedAtIsNull();
    T findByUuidAndRemovedAtIsNull();
    T findByIdAndRemovedAtIsNull();
}
