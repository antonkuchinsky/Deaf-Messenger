package com.who.userservice.user;

import com.who.userservice.entity.User;
import com.who.userservice.exception.InvalidDataException;
import com.who.userservice.repository.UserRepository;
import com.who.userservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserByIdUserFoundShouldReturnUser() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        user.setUsername("testuser");
        user.setIsActive(true);
        user.setLastActive(ZonedDateTime.now());

        when(userRepository.getUserByID(userId)).thenReturn(Optional.of(user));

        User result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getIsActive(), result.getIsActive());
        assertEquals(user.getLastActive(), result.getLastActive());
    }

    @Test
    public void getUserByIdUserNotFoundShouldThrowInvalidDataException() {
        UUID userId = UUID.randomUUID();

        when(userRepository.getUserByID(userId)).thenReturn(Optional.empty());

        assertThrows(InvalidDataException.class, () -> userService.getUserById(userId));
    }

    @Test
    public void updateUsernameShouldUpdateUsername() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        user.setUsername("oldusername");

        String newUsername = "newusername";

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.updateUsername(user, newUsername);

        assertNotNull(result);
        assertEquals(newUsername, result.getUsername());
    }
}
