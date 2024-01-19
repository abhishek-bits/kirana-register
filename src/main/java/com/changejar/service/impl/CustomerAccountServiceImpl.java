package com.changejar.service.impl;

import com.changejar.constant.TimeConstants;
import com.changejar.dto.BaseRequestDTO;
import com.changejar.dto.CustomerAccountDTO;
import com.changejar.dto.external.CurrencyDTO;
import com.changejar.entity.CustomerAccount;
import com.changejar.enums.ResourceType;
import com.changejar.exception.ResourceNotFoundException;
import com.changejar.repository.CustomerAccountRepository;
import com.changejar.service.CurrencyService;
import com.changejar.service.CustomerAccountService;
import com.changejar.service.KiranaStoreService;
import com.changejar.service.UserService;
import com.changejar.util.LocalDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of contracts enlisted in CustomerAccountService interface.
 *
 * @author Abhishek Sharma.
 */
@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    @Autowired
    private UserService userService;

    @Autowired
    private KiranaStoreService kiranaStoreService;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private CurrencyService currencyService;

    /*
     * Eliminating Partial DB writes using @Transactional annotation by rollback in case of any RuntimeException.
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public CustomerAccount save(CustomerAccountDTO customerAccountDTO) {

        handleInvalidRequest(customerAccountDTO);

        return save(new CustomerAccount(
                null,
                customerAccountDTO.getKiranaStoreId(),
                customerAccountDTO.getCustomerId(),
                customerAccountDTO.getAmountPending(),
                System.currentTimeMillis(),
                System.currentTimeMillis()
        ));
    }

    /*
     * Eliminating Partial DB writes using @Transactional annotation by rollback in case of any RuntimeException.
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public CustomerAccount save(CustomerAccount customerAccount) {
        if(customerAccount.getId() != null) {
            customerAccount.setLastUpdatedAt(System.currentTimeMillis());
        }
        return customerAccountRepository.save(customerAccount);
    }

    @Override
    public Collection<CustomerAccountDTO> getUserAccounts(BaseRequestDTO baseRequestDTO) {
        Collection<CustomerAccount> customerAccounts;

        // Assuming fromMillis is not null.
        if(baseRequestDTO.getToMillis() == null) {
            if(baseRequestDTO.getKiranaStoreId() == null) {
                if(baseRequestDTO.getUserId() == null) {
                    customerAccounts = customerAccountRepository.findByCreatedAtBetween(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getFromMillis() + TimeConstants.DEFAULT_OFFSET_HOURS_MILLISECONDS
                    );
                } else {
                    customerAccounts = customerAccountRepository.findByCreatedAtBetweenAndUserId(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getFromMillis() + TimeConstants.DEFAULT_OFFSET_HOURS_MILLISECONDS,
                            baseRequestDTO.getUserId()
                    );
                }
            } else {
                if(baseRequestDTO.getUserId() == null) {
                    customerAccounts = customerAccountRepository.findByCreatedAtBetweenAndKiranaStoreId(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getFromMillis() + TimeConstants.DEFAULT_OFFSET_HOURS_MILLISECONDS,
                            baseRequestDTO.getKiranaStoreId()
                    );
                } else {
                    customerAccounts = customerAccountRepository.findByCreatedAtBetweenAndKiranaStoreIdAndUserId(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getFromMillis() + TimeConstants.DEFAULT_OFFSET_HOURS_MILLISECONDS,
                            baseRequestDTO.getKiranaStoreId(),
                            baseRequestDTO.getUserId()
                    );
                }
            }
        } else {
            if(baseRequestDTO.getKiranaStoreId() == null) {
                if(baseRequestDTO.getUserId() == null) {
                    customerAccounts = customerAccountRepository.findByCreatedAtBetween(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getToMillis()
                    );
                } else {
                    customerAccounts = customerAccountRepository.findByCreatedAtBetweenAndUserId(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getToMillis(),
                            baseRequestDTO.getUserId()
                    );
                }
            } else {
                if(baseRequestDTO.getUserId() == null) {
                    customerAccounts = customerAccountRepository.findByCreatedAtBetweenAndKiranaStoreId(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getToMillis(),
                            baseRequestDTO.getKiranaStoreId()
                    );
                } else {
                    customerAccounts = customerAccountRepository.findByCreatedAtBetweenAndKiranaStoreIdAndUserId(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getToMillis(),
                            baseRequestDTO.getKiranaStoreId(),
                            baseRequestDTO.getUserId()
                    );
                }
            }
        }

        // Convert to target currency
        CurrencyDTO currencyDTO = currencyService.getCurrencyConversionRates();

        return customerAccounts.stream()
                .map(customerAccount -> new CustomerAccountDTO(
                        customerAccount.getUserId(),
                        customerAccount.getKiranaStoreId(),
                        customerAccount.getAmountPending() * currencyDTO.getRates().get(
                                baseRequestDTO.getCurrencyType().toString()
                        ),
                        LocalDateTimeUtils.getLocalDateTime(customerAccount.getCreatedAt()).toString(),
                        LocalDateTimeUtils.getLocalDateTime(customerAccount.getLastUpdatedAt()).toString()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerAccount> getByKiranaStoreIdAndUserId(Long kiranaStoreId, Long userId) {
        return customerAccountRepository.findByKiranaStoreIdAndUserId(kiranaStoreId, userId);
    }

    private void handleInvalidRequest(CustomerAccountDTO customerAccountDTO) {
        if(userService.getById(customerAccountDTO.getCustomerId()).isEmpty()) {
            throw new ResourceNotFoundException(ResourceType.USER, customerAccountDTO.getCustomerId());
        }
        if(kiranaStoreService.getById(customerAccountDTO.getKiranaStoreId()).isEmpty()) {
            throw new ResourceNotFoundException(ResourceType.KIRANA_STORE, customerAccountDTO.getKiranaStoreId());
        }
    }
}
