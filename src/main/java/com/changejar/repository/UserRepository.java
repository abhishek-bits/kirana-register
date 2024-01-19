package com.changejar.repository;

import com.changejar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This repository provides basic CRUD operations for User entity.
 *
 * @author Abhishek Sharma
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Fetches the user for the given id.
     * @param id the id for which a user will be fetched.
     * @return an Optional of type User.
     */
    Optional<User> getUserById(Long id);
}
