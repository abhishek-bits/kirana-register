package com.changejar.service;

import com.changejar.dto.UserDTO;

public interface UserService {

    void save(UserDTO userDTO);

    void deleteById(Long userId);
}
