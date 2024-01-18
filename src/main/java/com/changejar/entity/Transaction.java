package com.changejar.entity;

import com.changejar.enums.CurrencyType;
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
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(updatable = false)
    private Long kiranaStoreId;

    @Column(updatable = false)
    private Long customerId;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private CurrencyType currencyType;

    private Double amountPending;
    private Double amountPaid;
}
