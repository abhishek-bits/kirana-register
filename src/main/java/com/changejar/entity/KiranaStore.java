package com.changejar.entity;

import com.changejar.enums.StoreStatus;

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
    private Long id;

    private Long ownerId;

    private String city;
    private String state;
    private Integer pincode;

    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;
}
