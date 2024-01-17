package com.changejar.entity;

import com.changejar.enums.StoreStatus;

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

@Entity
@Table(name="kirana_store")
@NoArgsConstructor
@AllArgsConstructor
public class KiranaStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long ownerId;

    String city;
    String state;
    Integer pincode;

    @Enumerated(EnumType.STRING)
    StoreStatus storeStatus;
}
