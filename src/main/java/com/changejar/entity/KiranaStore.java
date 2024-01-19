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
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * This entity is used to represent a Kirana Store.
 *
 * @author Abhishek Sharma
 */
@Data
@Entity
@Table(name="kirana_store")
@NoArgsConstructor
@AllArgsConstructor
public class KiranaStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field signifies the id of the User entity. Basically tells who is the owner of this kirana-store.
     */
    private Long userId;

    /**
     * This field signifies the city in which this kirana store resides.
     */
    private String city;

    /**
     * This field signifies the state in which this kirana store resides.
     */
    private String state;

    /**
     * This field signfies the pincode in which this kirana store resides.
     */
    private Integer pincode;

    /**
     * This field signifies the status whether the store is open or closed.
     */
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;
}
