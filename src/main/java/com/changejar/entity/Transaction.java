package com.changejar.entity;

import com.changejar.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * This entity is used to represent a transaction.
 *
 * @author Abhishek Sharma
 */
@Data
@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * This field signifies the id of the Kirana Store entity for which this transaction will be recorded.
     */
    @Column(updatable = false)
    private Long kiranaStoreId;

    /**
     * This field signifies the id of the User entity for which this transaction will be recorded.
     */
    @Column(updatable = false)
    private Long userId;

    /**
     * This field signifies the time instance at which this transaction was created.
     */
    @Column(updatable = false)
    private Long createdAt;

    /**
     * This field signifies the type of the transaction i.e. whether CREDIT or DEBIT.
     */
    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private TransactionType transactionType;

    /**
     * This field signifies the amount involved in this transaction.
     */
    private Double amount;
}
