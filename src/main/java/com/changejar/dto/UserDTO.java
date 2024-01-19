package com.changejar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * This DTO is used to store and retrieve information for User entity.
 *
 * @author Abhishek Sharma
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {
    /**
     * This field signifies the id of the User entity.
     */
    private Long id;

    /**
     * This field signifies the name of this user.
     */
    private String name;

    /**
     * This field signifies the email of this user.
     */
    private String email;

    /**
     * This field signifies the phone number of this user.
     */
    private Long phone;
}
