package com.changejar.service;

import com.changejar.dto.TransactionDTO;
import com.changejar.dto.TransactionRequestDTO;
import com.changejar.dto.UserActionDTO;
import com.changejar.entity.Transaction;
import com.changejar.enums.CurrencyType;
import com.changejar.enums.TransactionType;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.Collection;

public interface TransactionService {

    Transaction save(TransactionDTO transactionDTO);

    Collection<TransactionDTO> getTransactions(TransactionRequestDTO transactionRequestDTO);
}
