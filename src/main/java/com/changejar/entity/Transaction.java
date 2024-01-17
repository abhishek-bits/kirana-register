package com.changejar.entity;

import com.changejar.enums.CurrencyType;
import com.changejar.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(updatable = false)
    Long kiranaStoreId;

    @Column(updatable = false)
    Long customerId;

    @Column(updatable = false)
    LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    CurrencyType currencyType;

    Double amountPending;
    Double amountPaid;
}
