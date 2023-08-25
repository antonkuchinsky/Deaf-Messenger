package com.who.userservice.service;

import com.who.userservice.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {
    User getUserById(UUID id);
    User updateUsername(User user, String username);

}
