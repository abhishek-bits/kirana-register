package com.changejar.service;

import com.changejar.dto.KiranaStoreDTO;
import com.changejar.entity.KiranaStore;

import java.util.Collection;

public interface KiranaStoreService {

    void save(KiranaStoreDTO kiranaStoreDTO);

    void deleteById(Long kiranaStoreId);
}
