package com.changejar.service;

import com.changejar.dto.UserDTO;
import com.changejar.entity.User;

import java.util.Optional;

/**
 * This service contains all the required contracts to support Kirana Register application.
 *
 * @author Abhishek Sharma
 */
public interface UserService {

    /**
     * Finds the user for the given id.
     * @param userId the id for which user will be searched for.
     * @return an Optional of type User.
     */
    Optional<User> getById(Long userId);

    /**
     * Stores the gives user in the DB.
     * @param userDTO dto containing the required details of the user.
     * @return the created User entity.
     */
    User save(UserDTO userDTO);
}
