package com.changejar.service.impl;

import com.changejar.constant.TimeConstants;
import com.changejar.dto.TransactionDTO;
import com.changejar.dto.BaseRequestDTO;
import com.changejar.dto.external.CurrencyDTO;
import com.changejar.entity.Transaction;
import com.changejar.entity.CustomerAccount;
import com.changejar.enums.TransactionType;
import com.changejar.repository.TransactionRepository;
import com.changejar.service.CurrencyService;
import com.changejar.service.TransactionService;
import com.changejar.service.CustomerAccountService;
import com.changejar.util.LocalDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of contracts enlisted in TransactionService interface.
 *
 * @author Abhishek Sharma
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private TransactionRepository transactionRepository;

    /*
     * Eliminating RACE CONDITION and Partial DB writes using @Transactional annotation
     * - by applying a stricter form of isolation.
     * - by supporting rollback in case of any RuntimeException.
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = RuntimeException.class)
    public Transaction save(TransactionDTO transactionDTO) {

        if(transactionDTO == null) {
            return null;
        }

        Optional<CustomerAccount> userAccountOptional = customerAccountService.getByKiranaStoreIdAndUserId(
                transactionDTO.getKiranaStoreId(),
                transactionDTO.getCustomerId());

        if(userAccountOptional.isEmpty()) {
            throw new IllegalStateException("Invalid request.");
        }

        CurrencyDTO currencyDTO = currencyService.getCurrencyConversionRates();

        Transaction transaction = transactionRepository.save(
                new Transaction(
                    null,
                    transactionDTO.getKiranaStoreId(),
                    transactionDTO.getCustomerId(),
                    System.currentTimeMillis(),
                    transactionDTO.getTransactionType(),
                    transactionDTO.getAmount() / currencyDTO.getRates().get(
                            transactionDTO.getCurrencyType().toString())
                )
        );

        CustomerAccount customerAccount = userAccountOptional.get();

        if(transaction.getTransactionType().equals(TransactionType.CREDIT)) {
            // If it is a CREDIT type transaction, then it means that
            // the customer has done repayment of his/her pending amount.
            customerAccount.setAmountPending(customerAccount.getAmountPending() - transaction.getAmount());
        } else {
            // If it is a DEBIT type transaction, then it means that
            // the customer has bought goods for which he/she will pay later.
            customerAccount.setAmountPending(customerAccount.getAmountPending() + transaction.getAmount());
        }

        customerAccountService.save(customerAccount);

        return transaction;
    }

    @Override
    public Collection<TransactionDTO> getTransactions(BaseRequestDTO baseRequestDTO) {

        Collection<Transaction> transactions;

        if(baseRequestDTO.getFromMillis() == null) {
            throw new IllegalArgumentException("Invalid Request: fromMillis is a required field.");
        }

        if(baseRequestDTO.getToMillis() == null) {
            if(baseRequestDTO.getKiranaStoreId() == null) {
                if(baseRequestDTO.getUserId() == null) {
                    transactions = transactionRepository.findByCreatedAtBetween(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getFromMillis() + TimeConstants.DEFAULT_OFFSET_HOURS_MILLISECONDS
                    );
                } else {
                    transactions = transactionRepository.findByCreatedAtBetweenAndUserId(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getFromMillis() + TimeConstants.DEFAULT_OFFSET_HOURS_MILLISECONDS,
                            baseRequestDTO.getUserId()
                    );
                }
            } else {
                if(baseRequestDTO.getUserId() == null) {
                    transactions = transactionRepository.findByCreatedAtBetweenAndKiranaStoreId(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getFromMillis() + TimeConstants.DEFAULT_OFFSET_HOURS_MILLISECONDS,
                            baseRequestDTO.getKiranaStoreId()
                    );
                } else {
                    transactions = transactionRepository.findByCreatedAtBetweenAndKiranaStoreIdAndUserId(
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
                    transactions = transactionRepository.findByCreatedAtBetween(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getToMillis()
                    );
                } else {
                    transactions = transactionRepository.findByCreatedAtBetweenAndUserId(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getToMillis(),
                            baseRequestDTO.getUserId()
                    );
                }
            } else {
                if(baseRequestDTO.getUserId() == null) {
                    transactions = transactionRepository.findByCreatedAtBetweenAndKiranaStoreId(
                            baseRequestDTO.getFromMillis(),
                            baseRequestDTO.getToMillis(),
                            baseRequestDTO.getKiranaStoreId()
                    );
                } else {
                    transactions = transactionRepository.findByCreatedAtBetweenAndKiranaStoreIdAndUserId(
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

        return transactions.stream()
                .map(transaction -> new TransactionDTO(
                        transaction.getKiranaStoreId(),
                        transaction.getUserId(),
                        LocalDateTimeUtils.getLocalDateTime(transaction.getCreatedAt()).toString(),
                        transaction.getTransactionType(),
                        baseRequestDTO.getCurrencyType(),
                        transaction.getAmount() * currencyDTO.getRates().get(
                                baseRequestDTO.getCurrencyType().toString())))
                .collect(Collectors.toList());
    }
}
