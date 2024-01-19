package com.changejar.service;

import com.changejar.dto.TransactionDTO;
import com.changejar.dto.BaseRequestDTO;
import com.changejar.entity.Transaction;

import java.util.Collection;

/**
 * This service is the heart of the Kirana Register application.
 * It contains all the contracts required to support the application.
 *
 * @author Abhishek Sharma
 */
public interface TransactionService {

    /**
     * Stores the given transaction details into the DB.
     * @param transactionDTO dto containing the required details for Transaction entity.
     * @return the created Transaction entity.
     */
    Transaction save(TransactionDTO transactionDTO);

    /**
     * Given the criteria to fetch, filter and group the transactions, return the list of all such transactions.
     * @param baseRequestDTO dto that enlists basic filtering and grouping fields.
     * @return a Collection of type TransactionDTO.
     */
    Collection<TransactionDTO> getTransactions(BaseRequestDTO baseRequestDTO);
}
