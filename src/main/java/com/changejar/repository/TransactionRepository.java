package com.changejar.repository;

import com.changejar.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Collection<Transaction> findByCreatedAtBetween(Long fromMillis, Long toMillis);
    
    Collection<Transaction> findByCreatedAtBetweenAndUserId(Long fromMillis, Long toMillis, Long userId);

    Collection<Transaction> findByCreatedAtBetweenAndKiranaStoreId(Long fromMillis, Long toMillis, Long kiranaStoreId);

    Collection<Transaction> findByCreatedAtBetweenAndKiranaStoreIdAndUserId(Long fromMillis, Long toMillis, Long kiranaStoreId, Long userId);
}
