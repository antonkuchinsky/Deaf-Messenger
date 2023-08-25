package com.who.userservice.service.impl;

import com.who.userservice.entity.User;
import com.who.userservice.exception.InvalidDataException;
import com.who.userservice.repository.UserRepository;
import com.who.userservice.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.getUserByID(id)
                .orElseThrow(()-> new InvalidDataException("We didn't find such a player, try again (", "User not found"));
    }

    @Override
    @Transactional
    public User updateUsername(@NotNull User user, String username) {
        user.setUsername(username);
        return userRepository.save(user);
    }
}
