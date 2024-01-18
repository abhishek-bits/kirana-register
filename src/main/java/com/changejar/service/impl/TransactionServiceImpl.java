package com.changejar.service.impl;

import com.changejar.constant.TransactionConstants;
import com.changejar.dto.TransactionDTO;
import com.changejar.dto.TransactionRequestDTO;
import com.changejar.dto.external.CurrencyDTO;
import com.changejar.entity.Transaction;
import com.changejar.entity.UserAction;
import com.changejar.enums.TransactionType;
import com.changejar.repository.TransactionRepository;
import com.changejar.service.CurrencyService;
import com.changejar.service.KiranaStoreService;
import com.changejar.service.TransactionService;
import com.changejar.service.UserActionService;
import com.changejar.service.UserService;
import com.changejar.util.LocalDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserActionService userActionService;

    @Autowired
    private KiranaStoreService kiranaStoreService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction save(TransactionDTO transactionDTO) {

        if(transactionDTO == null) {
            return null;
        }

        Optional<UserAction> userActionOptional = userActionService.getByKiranaStoreIdAndUserId(
                transactionDTO.getKiranaStoreId(),
                transactionDTO.getCustomerId());

        if(userActionOptional.isEmpty()) {
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

        UserAction userAction = userActionOptional.get();

        if(transactionDTO.getTransactionType().equals(TransactionType.CREDIT)) {
            userAction.setAmountPending(userAction.getAmountPending() - transaction.getAmount());
        } else {
            userAction.setAmountPending(userAction.getAmountPending() + transaction.getAmount());
        }

        userActionService.save(userAction);

        return transaction;
    }

    @Override
    public Collection<TransactionDTO> getTransactions(TransactionRequestDTO transactionRequestDTO) {

        Collection<Transaction> transactions;

        // Assuming fromDateTime is not null
        if(transactionRequestDTO.getToMillis() == null) {
            if(transactionRequestDTO.getKiranaStoreId() == null) {
                if(transactionRequestDTO.getUserId() == null) {
                    transactions = transactionRepository.findByCreatedAtBetween(
                            transactionRequestDTO.getFromMillis(),
                            transactionRequestDTO.getFromMillis() + TransactionConstants.TRANSACTION_FETCH_DEFAULT_OFFSET_HOURS_MILLISECONDS
                    );
                } else {
                    transactions = transactionRepository.findByCreatedAtBetweenAndUserId(
                            transactionRequestDTO.getFromMillis(),
                            transactionRequestDTO.getFromMillis() + TransactionConstants.TRANSACTION_FETCH_DEFAULT_OFFSET_HOURS_MILLISECONDS,
                            transactionRequestDTO.getUserId()
                    );
                }
            } else {
                if(transactionRequestDTO.getUserId() == null) {
                    transactions = transactionRepository.findByCreatedAtBetweenAndKiranaStoreId(
                            transactionRequestDTO.getFromMillis(),
                            transactionRequestDTO.getFromMillis() + TransactionConstants.TRANSACTION_FETCH_DEFAULT_OFFSET_HOURS_MILLISECONDS,
                            transactionRequestDTO.getKiranaStoreId()
                    );
                } else {
                    transactions = transactionRepository.findByCreatedAtBetweenAndKiranaStoreIdAndUserId(
                            transactionRequestDTO.getFromMillis(),
                            transactionRequestDTO.getFromMillis() + TransactionConstants.TRANSACTION_FETCH_DEFAULT_OFFSET_HOURS_MILLISECONDS,
                            transactionRequestDTO.getKiranaStoreId(),
                            transactionRequestDTO.getUserId()
                    );
                }
            }
        } else {
            if(transactionRequestDTO.getKiranaStoreId() == null) {
                if(transactionRequestDTO.getUserId() == null) {
                    transactions = transactionRepository.findByCreatedAtBetween(
                            transactionRequestDTO.getFromMillis(),
                            transactionRequestDTO.getToMillis()
                    );
                } else {
                    transactions = transactionRepository.findByCreatedAtBetweenAndUserId(
                            transactionRequestDTO.getFromMillis(),
                            transactionRequestDTO.getToMillis(),
                            transactionRequestDTO.getUserId()
                    );
                }
            } else {
                if(transactionRequestDTO.getUserId() == null) {
                    System.out.println("Groing correct");
                    transactions = transactionRepository.findByCreatedAtBetweenAndKiranaStoreId(
                            transactionRequestDTO.getFromMillis(),
                            transactionRequestDTO.getToMillis(),
                            transactionRequestDTO.getKiranaStoreId()
                    );
                } else {
                    transactions = transactionRepository.findByCreatedAtBetweenAndKiranaStoreIdAndUserId(
                            transactionRequestDTO.getFromMillis(),
                            transactionRequestDTO.getToMillis(),
                            transactionRequestDTO.getKiranaStoreId(),
                            transactionRequestDTO.getUserId()
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
                        transactionRequestDTO.getCurrencyType(),
                        transaction.getAmount() * currencyDTO.getRates().get(
                                transactionRequestDTO.getCurrencyType().toString()))
                ).collect(Collectors.toList());
    }
}
