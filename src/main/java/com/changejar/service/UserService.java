package com.changejar.service;

import com.changejar.dto.UserDTO;
import com.changejar.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getById(Long userId);
    User save(UserDTO userDTO);
}
