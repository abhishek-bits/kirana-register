package com.changejar.dto;

import com.changejar.enums.CurrencyType;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * This DTO is the base for all request payloads received from API if needed.
 * It helps us to group the data depending on the given field values.
 *
 * @author Abhishek Sharma
 */
@Getter
@ToString
public class BaseRequestDTO implements Serializable {

    /**
     * Time in milliseconds which tells us the start date from which the data has to fetch.
     * It is a mandatory field and cannot be null.
     */
    Long fromMillis;

    /**
     * Time in milliseconds which tells us the start date from which the data has to fetch.
     * If this field is null then the data is fetched up to 24 hours from given fromMillis (start-date).
     */
    @Nullable
    Long toMillis;

    /**
     * This field signifies the currency to which all the amounts will be converted and shown.
     * If this field is null then the default currency will be "USD".
     */
    CurrencyType currencyType;

    /**
     * This field signifies the id of the KiranaStore entity.
     */
    Long kiranaStoreId;

    /**
     * This field signifies the id of the User entity.
     */
    Long userId;
}
