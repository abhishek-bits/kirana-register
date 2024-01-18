package com.changejar.dto;

import com.changejar.enums.CurrencyType;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class TransactionRequestDTO implements Serializable {
    Long fromMillis;
    @Nullable
    Long toMillis;
    CurrencyType currencyType;
    Long kiranaStoreId;
    Long userId;
}
