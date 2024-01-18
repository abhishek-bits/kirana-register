package com.changejar.dto;

import com.changejar.enums.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KiranaStoreDTO implements Serializable {
    private Long id;
    private Long ownerId;
    private String city;
    private String state;
    private Integer pincode;
    private StoreStatus storeStatus;
}
