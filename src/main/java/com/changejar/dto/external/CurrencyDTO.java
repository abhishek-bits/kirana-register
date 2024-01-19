package com.changejar.dto.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * This DTO is used to parse and map the API response from currency conversion API.
 *
 * @author Abhishek Sharma
 */
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO {

    /**
     * This will hold all the currency conversion rates from USD to
     * country-name as its key and the respective conversion rate as its value.
     */
    private Map<String, Double> rates;
}
