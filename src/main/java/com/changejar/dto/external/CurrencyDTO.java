package com.changejar.dto.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO {
    private Map<String, Double> rates;
}
