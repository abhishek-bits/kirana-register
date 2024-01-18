package com.changejar.service;

import com.changejar.dto.KiranaStoreDTO;
import com.changejar.entity.KiranaStore;

import java.util.Collection;
import java.util.Optional;

public interface KiranaStoreService {
    Optional<KiranaStore> getById(Long kiranaStoreId);
    KiranaStore save(KiranaStoreDTO kiranaStoreDTO);
}
