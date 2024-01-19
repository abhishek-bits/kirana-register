package com.changejar.repository;

import com.changejar.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

/**
 * This repository provides basic CRUD operations for Transaction entity.
 *
 * @author Abhishek Sharma
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    /**
     * Finds the list of Transaction entities for the given params.
     * @param fromMillis the time-instance in milliseconds from which the data will be fetched.
     * @param toMillis the time-instance in milliseconds to which the data will be fetched.
     * @return a Collection of Transaction entities.
     */
    Collection<Transaction> findByCreatedAtBetween(Long fromMillis, Long toMillis);

    /**
     * Finds the list of Transaction entities for the given params.
     * @param fromMillis the time-instance in milliseconds from which the data will be fetched.
     * @param toMillis the time-instance in milliseconds to which the data will be fetched.
     * @param userId the id of the user.
     * @return a Collection of Transaction entities.
     */
    Collection<Transaction> findByCreatedAtBetweenAndUserId(Long fromMillis, Long toMillis, Long userId);

    /**
     * Finds the list of Transaction entities for the given params.
     * @param fromMillis the time-instance in milliseconds from which the data will be fetched.
     * @param toMillis the time-instance in milliseconds to which the data will be fetched.
     * @param kiranaStoreId the id of the kirana-store.
     * @return a Collection of Transaction entities.
     */
    Collection<Transaction> findByCreatedAtBetweenAndKiranaStoreId(Long fromMillis, Long toMillis, Long kiranaStoreId);

    /**
     * Finds the list of Transaction entities for the given params.
     * @param fromMillis the time-instance in milliseconds from which the data will be fetched.
     * @param toMillis the time-instance in milliseconds to which the data will be fetched.
     * @param kiranaStoreId the id of the kirana-store.
     * @param userId the id of the user.
     * @return a Collection of Transaction entities.
     */
    Collection<Transaction> findByCreatedAtBetweenAndKiranaStoreIdAndUserId(Long fromMillis, Long toMillis, Long kiranaStoreId, Long userId);
}
