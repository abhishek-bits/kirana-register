package com.changejar.repository;

import com.changejar.entity.KiranaStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This repository provides basic CRUD operations for KiranaStore entity.
 *
 * @author Abhishek Sharma
 */
@Repository
public interface KiranaStoreRepository extends JpaRepository<KiranaStore, Long> {
    /**
     * Fetches the kirana store for the given id.
     *
     * @param id the id for which a kirana-store will be fetched.
     * @return an Optional of type KiranaStore
     */
    Optional<KiranaStore> getKiranaStoreById(Long id);
}
