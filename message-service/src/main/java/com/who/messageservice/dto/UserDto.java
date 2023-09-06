package com.who.messageservice.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public record UserDto(
    UUID id,
    String username,
    String bio,
    Boolean isActive,
    ZonedDateTime lastActive
) {
}
