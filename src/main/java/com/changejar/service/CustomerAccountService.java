package com.changejar.service;

import com.changejar.dto.BaseRequestDTO;
import com.changejar.dto.CustomerAccountDTO;
import com.changejar.entity.CustomerAccount;

import java.util.Collection;
import java.util.Optional;

public interface CustomerAccountService {

    CustomerAccount save(CustomerAccountDTO customerAccountDTO);

    CustomerAccount save(CustomerAccount customerAccount);

    Collection<CustomerAccountDTO> getUserAccounts(BaseRequestDTO baseRequestDTO);

    Optional<CustomerAccount> getByKiranaStoreIdAndUserId(Long kiranaStoreId, Long customerId);
}
