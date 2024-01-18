package com.changejar.repository;

import com.changejar.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
    Optional<CustomerAccount> findByKiranaStoreIdAndUserId(Long kiranaStoreId, Long userId);

    Collection<CustomerAccount> findByCreatedAtBetween(Long fromMillis, Long toMillis);

    Collection<CustomerAccount> findByCreatedAtBetweenAndUserId(Long fromMillis, Long toMillis, Long userId);

    Collection<CustomerAccount> findByCreatedAtBetweenAndKiranaStoreId(Long fromMillis, Long toMillis, Long kiranaStoreId);

    Collection<CustomerAccount> findByCreatedAtBetweenAndKiranaStoreIdAndUserId(Long fromMillis, Long toMillis, Long kiranaStoreId, Long userId);
}
