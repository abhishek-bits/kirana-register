package com.changejar.repository;

import com.changejar.entity.UserAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserActionRepository extends JpaRepository<UserAction, Long> {
    Optional<UserAction> findByKiranaStoreIdAndUserId(Long kiranaStoreId, Long userId);
}
