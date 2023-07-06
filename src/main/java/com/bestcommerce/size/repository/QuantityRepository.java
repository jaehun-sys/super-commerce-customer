package com.bestcommerce.size.repository;

import com.bestcommerce.size.entity.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;
import java.util.Optional;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select q from quantity q where q.id = :id")
    Optional<Quantity> findWithIdForUpdate(@Param("id") Long id);
}
