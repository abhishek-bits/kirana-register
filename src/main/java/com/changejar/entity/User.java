package com.changejar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * This entity is used to represent a User.
 *
 * @author Abhishek Sharma
 */
@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field signifies the name of this user.
     */
    private String name;

    /**
     * This field signifies the email of this user.
     */
    @Column(unique = true)
    private String email;

    /**
     * This field signifies the phone number of this user.
     */
    @Column(unique = true)
    private Long phone;
}
