package com.changejar.service.impl;

import com.changejar.dto.KiranaStoreDTO;
import com.changejar.entity.KiranaStore;
import com.changejar.repository.KiranaStoreRepository;
import com.changejar.service.KiranaStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of contracts enlisted in KiranaStoreService interface.
 *
 * @author Abhishek Sharma
 */
@Service
public class KiranaStoreServiceImpl implements KiranaStoreService {

    @Autowired
    private KiranaStoreRepository kiranaStoreRepository;

    @Override
    public Optional<KiranaStore> getById(Long kiranaStoreId) {
        return kiranaStoreRepository.getKiranaStoreById(kiranaStoreId);
    }

    @Override
    public KiranaStore save(KiranaStoreDTO kiranaStoreDTO) {
        return kiranaStoreRepository.save(new KiranaStore(
                null,
                kiranaStoreDTO.getOwnerId(),
                kiranaStoreDTO.getCity(),
                kiranaStoreDTO.getState(),
                kiranaStoreDTO.getPincode(),
                kiranaStoreDTO.getStoreStatus()
        ));
    }
}
