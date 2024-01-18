package com.changejar.dto;

import com.changejar.enums.CurrencyType;
import com.changejar.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    private Long kiranaStoreId;
    private Long customerId;
    private TransactionType transactionType;
    private CurrencyType currencyType;
    private Long amountPending;
    private Long amountPaid;
}
