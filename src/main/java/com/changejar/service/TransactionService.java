package com.changejar.service;

import com.changejar.dto.TransactionDTO;
import com.changejar.dto.BaseRequestDTO;
import com.changejar.entity.Transaction;

import java.util.Collection;

public interface TransactionService {

    Transaction save(TransactionDTO transactionDTO);

    Collection<TransactionDTO> getTransactions(BaseRequestDTO baseRequestDTO);
}
