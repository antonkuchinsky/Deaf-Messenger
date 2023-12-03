package com.who.userservice.controller;
import com.who.userservice.dto.ContactsRequestDto;
import com.who.userservice.dto.UserRequestDto;
import com.who.userservice.entity.User;
import com.who.userservice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{user_id}")
    public User getUser(@PathVariable("user_id") UUID id) {
        return userService.getUserById(id);
    }

    @PatchMapping("/{user_id}")
    public User updateUsername(@PathVariable("user_id") UUID id,
                               @RequestBody @NotNull @Valid ContactsRequestDto contactsRequestDto) {

        return userService.updateUsername(userService.getUserById(id), contactsRequestDto.username());
    }

}