package com.changejar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerAccountDTO implements Serializable {
    private Long customerId;
    private Long kiranaStoreId;
    private Double amountPending;
    private String createdAt;
    private String lastUpdatedAt;
}
