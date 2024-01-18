package com.changejar.dto;

import com.changejar.enums.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KiranaStoreDTO {
    private Long id;
    private Long ownerId;
    private String city;
    private String state;
    private Integer pincode;
    private StoreStatus storeStatus;
}
