package com.changejar.repository;

import com.changejar.entity.KiranaStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KiranaStoreRepository extends JpaRepository<KiranaStore, Long> {
    Optional<KiranaStore> getKiranaStoreById(Long id);
}
