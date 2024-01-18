package com.changejar.service;

import com.changejar.dto.TransactionDTO;
import com.changejar.dto.UserActionDTO;
import com.changejar.enums.CurrencyType;
import com.changejar.enums.TransactionType;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.Collection;

public interface TransactionService {

    void save(TransactionDTO transactionDTO);

    Collection<TransactionDTO> getTransactionsByDateRangeAndCurrencyTypeAndKiranaStoreIdAndUserId(
            LocalDateTime fromDateTime,
            @Nullable LocalDateTime toDateTime,
            CurrencyType currencyType,
            Long kiranaStoreId,
            Long userId);

    Collection<UserActionDTO> getUserActionsByDateRangeAndCurrencyTypeAndKiranaStoreIdAndTransactionType(
            LocalDateTime fromDateTime,
            @Nullable LocalDateTime toDateTime,
            CurrencyType currencyType,
            Long kiranaStoreId,
            @Nullable TransactionType transactionType);
}
