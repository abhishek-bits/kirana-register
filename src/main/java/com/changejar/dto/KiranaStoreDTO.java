package com.changejar.dto;

import com.changejar.enums.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * This DTO is used to store and retrieve information for KiranaStore entity.
 *
 * @author Abhishek Sharma
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KiranaStoreDTO implements Serializable {
    /**
     * This field signifies the id of the KiranaStore entity.
     */
    private Long id;

    /**
     * This field signifies the id of the User entity. Basically tells who is the owner of this kirana-store.
     */
    private Long ownerId;

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
    private StoreStatus storeStatus;
}
