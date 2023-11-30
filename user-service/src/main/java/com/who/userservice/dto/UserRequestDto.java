package com.who.userservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record UserRequestDto(
        UUID id,
        @NotNull(message = "Username is not null")
        @Pattern(message = "Bad formed person username: ${validatedValue}",
                regexp = "^[a-z A-Zа-яA-Я0-9_-]{2,255}$")
        @Length(min = 2, max = 20, message = "The length of the username must be from 2 to 20 characters")
        String username
) {
}
