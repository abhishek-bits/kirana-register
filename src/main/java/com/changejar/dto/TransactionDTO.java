package com.changejar.dto;

import com.changejar.enums.CurrencyType;
import com.changejar.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * This DTO is used to store and retrieve information for Transaction entity.
 *
 * @author Abhishek Sharma
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDTO implements Serializable {
    /**
     * This field signifies the id of the Kirana Store entity for which this transaction will be recorded.
     */
    private Long kiranaStoreId;

    /**
     * This field signifies the id of the User entity for which this transaction will be recorded.
     */
    private Long customerId;

    /**
     * This field signifies the time instance at which this transaction was created.
     */
    private String createdAt;

    /**
     * This field signifies the type of the transaction i.e. whether CREDIT or DEBIT.
     */
    private TransactionType transactionType;

    /**
     * This field signifies the type of the currency i.e. whether USD or INR, in which this transaction happened.
     */
    private CurrencyType currencyType;

    /**
     * This field signifies the amount involved in this transaction.
     */
    private Double amount;
}
