package com.changejar.service.impl;

import com.changejar.dto.UserDTO;
import com.changejar.entity.User;
import com.changejar.repository.UserRepository;
import com.changejar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getById(Long userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public User save(UserDTO userDTO) {
        return userRepository.save(new User(
                null,
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPhone()
        ));
    }

}
