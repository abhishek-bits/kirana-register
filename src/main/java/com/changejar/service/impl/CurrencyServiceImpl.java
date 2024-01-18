package com.changejar.service.impl;

import com.changejar.constant.ExternalAPIs;
import com.changejar.dto.external.CurrencyDTO;
import com.changejar.service.CurrencyService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Override
    public CurrencyDTO getCurrencyConversionRates() {
        return new RestTemplate().getForObject(ExternalAPIs.CURRENCY_CONVERSION_API, CurrencyDTO.class);
    }
}
