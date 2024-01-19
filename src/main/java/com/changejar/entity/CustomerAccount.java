package com.changejar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This entity is used to represent the customer accounts maintained at Kirana Stores.
 *
 * @author Abhishek Sharma
 */
@Data
@Entity
@Table(name = "customer_account")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field signifies the id of the KiranaStore entity in which this customer-account will be created.
     */
    @Column(updatable = false)
    private Long kiranaStoreId;

    /**
     * This field signifies the id of the User entity for which a customer-account will be created.
     */
    @Column(updatable = false)
    private Long userId;

    /**
     * This field signifies the pending amount of this customer (customerId) at this kirana-store (kiranaStoreId).
     */
    private Double amountPending;

    /**
     * This field signifies the time instance at which this account was opened at the kirana-store.
     */
    private Long createdAt;

    /**
     * This field signifies the time instance at which this account was last updated at the kirana-store.
     */
    private Long lastUpdatedAt;
}
