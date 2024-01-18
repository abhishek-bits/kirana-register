package com.changejar.dto;

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
public class UserActionDTO implements Serializable {
    private Long customerId;
    private String customerName;
    private Long customerPhone;
    private Long kiranaStoreId;
    private Long kiranaStorePincode;
    private Double amountPending;
    private String createdAt;
    private String lastUpdatedAt;
}
