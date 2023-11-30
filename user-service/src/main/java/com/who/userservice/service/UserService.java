package com.who.userservice.service;

import com.who.userservice.dto.UserRequestDto;
import com.who.userservice.entity.User;


import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
    User updateUsername(User user, String username);
    User createUser(UserRequestDto userRequestDto);

}
