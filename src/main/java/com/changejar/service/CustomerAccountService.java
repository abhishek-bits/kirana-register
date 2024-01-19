package com.changejar.service;

import com.changejar.dto.BaseRequestDTO;
import com.changejar.dto.CustomerAccountDTO;
import com.changejar.entity.CustomerAccount;

import java.util.Collection;
import java.util.Optional;

/**
 * This service maintains the customer accounts at Kirana Stores.
 * Depending on the transactions received the pending amounts in the
 * respective customer accounts should be consistently updated.
 *
 * @author Abhishek.
 */
public interface CustomerAccountService {

    /**
     * Saves the given customer-account in the DB on successful validation of the request.
     * @param customerAccountDTO dto containing all the required fields for CustomerAccount.
     * @return the created CustomerAccount entity.
     */
    CustomerAccount save(CustomerAccountDTO customerAccountDTO);

    /**
     * Saves / Updates the given CustomerAccount entity in the DB.
     * NOTE: this method should be used only when the input details are verified.
     * @param customerAccount the CustomerAccount entity.
     * @return the created / updated CustomerAccount entity.
     */
    CustomerAccount save(CustomerAccount customerAccount);

    /**
     * Given the criteria to fetch, filter and group the customer-accounts, return the list of all such customer-accounts.
     * @param baseRequestDTO dto that enlists basic filtering and grouping fields.
     * @return a Collection of type CustomerAccountDTO.
     */
    Collection<CustomerAccountDTO> getUserAccounts(BaseRequestDTO baseRequestDTO);

    /**
     * Finds the customer-account details for the given data.
     * @param kiranaStoreId the id of the Kirana store entity.
     * @param userId the id of the User entity.
     * @return
     */
    Optional<CustomerAccount> getByKiranaStoreIdAndUserId(Long kiranaStoreId, Long userId);
}
