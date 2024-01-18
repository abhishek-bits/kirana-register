package com.changejar.dto;

import com.changejar.enums.CurrencyType;
import com.changejar.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDTO implements Serializable {
    private Long kiranaStoreId;
    private Long customerId;
    private String createdAt;
    private TransactionType transactionType;
    private CurrencyType currencyType;
    private Double amount;
}
