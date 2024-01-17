package com.changejar.repository;

import com.changejar.entity.KiranaStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KiranaStoreRepository extends JpaRepository<KiranaStore, Long> {
}
