package com.changejar.service;

import com.changejar.dto.KiranaStoreDTO;
import com.changejar.entity.KiranaStore;

import java.util.Optional;

/**
 * This service provides contracts required to support Kirana Register application
 */
public interface KiranaStoreService {
    /**
     * Finds the kirana-store for the given id.
     * @param kiranaStoreId the id for which the KiranaStore entity will be fetched.
     * @return an Optional of type KiranaStore.
     */
    Optional<KiranaStore> getById(Long kiranaStoreId);

    /**
     * Stores the gives kirana-store in the DB.
     * @param kiranaStoreDTO dto containing the required details of the kirana-store.
     * @return the created KiranaStore entity.
     */
    KiranaStore save(KiranaStoreDTO kiranaStoreDTO);
}
