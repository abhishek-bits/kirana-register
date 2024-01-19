package com.changejar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * This DTO is used to store and retrieve information for CustomerAccount entity.
 *
 * @author Abhishek Sharma
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerAccountDTO implements Serializable {
    /**
     * This field signifies the id of the User entity for which a customer-account will be created.
     */
    private Long customerId;

    /**
     * This field signifies the id of the KiranaStore entity in which this customer-account will be created.
     */
    private Long kiranaStoreId;

    /**
     * This field signifies the pending amount of this customer (customerId) at this kirana-store (kiranaStoreId).
     */
    private Double amountPending;

    /**
     * This field signifies the time instance at which this account was opened at the kirana-store.
     */
    private String createdAt;

    /**
     * This field signifies the time instance at which this account was last updated at the kirana-store.
     */
    private String lastUpdatedAt;
}
