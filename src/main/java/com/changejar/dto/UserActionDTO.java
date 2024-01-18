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
public class UserActionDTO {
    private Long userId;
    private String userName;
    private Long userPhone;
    private Double amountPending;
    private Double amountPaid;
    private CurrencyType currencyType;
    private TransactionType transactionType;
}
