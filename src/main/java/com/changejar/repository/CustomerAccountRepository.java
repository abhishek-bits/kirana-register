package com.changejar.repository;

import com.changejar.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * This repository provides basic CRUD operations for CustomerAccount entity.
 *
 * @author Abhishek Sharma
 */
@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {

    /**
     * Finds the CustomerAccount entity for the given params.
     * @param kiranaStoreId the id of the kirana-store.
     * @param userId the id of the user.
     * @return an Optional of type CustomerAccount
     */
    Optional<CustomerAccount> findByKiranaStoreIdAndUserId(Long kiranaStoreId, Long userId);

    /**
     * Finds the list of CustomerAccount entities for the given params.
     * @param fromMillis the time-instance in milliseconds from which the data will be fetched.
     * @param toMillis the time-instance in milliseconds to which the data will be fetched.
     * @return a Collection of CustomerAccount entities.
     */
    Collection<CustomerAccount> findByCreatedAtBetween(Long fromMillis, Long toMillis);

    /**
     * Finds the list of Customer Account entities for the given params.
     * @param fromMillis the time-instance in milliseconds from which the data will be fetched.
     * @param toMillis the time-instance in milliseconds to which the data will be fetched.
     * @param userId the id of the user.
     * @return a Collection of CustomerAccount entities.
     */
    Collection<CustomerAccount> findByCreatedAtBetweenAndUserId(Long fromMillis, Long toMillis, Long userId);

    /**
     * Finds the list of Customer Account entities for the given params.
     * @param fromMillis the time-instance in milliseconds from which the data will be fetched.
     * @param toMillis the time-instance in milliseconds to which the data will be fetched.
     * @param kiranaStoreId the id of the kirana-store.
     * @return a Collection of CustomerAccount entities.
     */
    Collection<CustomerAccount> findByCreatedAtBetweenAndKiranaStoreId(Long fromMillis, Long toMillis, Long kiranaStoreId);

    /**
     * Finds the list of Customer Account entities for the given params.
     * @param fromMillis the time-instance in milliseconds from which the data will be fetched.
     * @param toMillis the time-instance in milliseconds to which the data will be fetched.
     * @param kiranaStoreId the id of the kirana-store.
     * @param userId the id of the user.
     * @return a Collection of CustomerAccount entities.
     */
    Collection<CustomerAccount> findByCreatedAtBetweenAndKiranaStoreIdAndUserId(Long fromMillis, Long toMillis, Long kiranaStoreId, Long userId);
}
