package com.changejar.dto.external;

import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Data
@Getter
public class CurrencyDTO {
    private Map<String, Double> rates;
}
