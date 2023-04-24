package com.bestcommerce.customer.size;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from size s where s.sizeId = :id")
    Optional<Size> findWithIdForUpdate(@Param("id") Long id);
}
