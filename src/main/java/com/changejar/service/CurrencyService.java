package com.changejar.service;

import com.changejar.dto.external.CurrencyDTO;

/**
 * This is a helper service to fetch the currency conversion rates.
 *
 * @author Abhishek Sharma
 */
public interface CurrencyService {
    /**
     * Fetches the currency conversion rates
     * @return dto containing the currency conversion rates.
     */
    CurrencyDTO getCurrencyConversionRates();
}
