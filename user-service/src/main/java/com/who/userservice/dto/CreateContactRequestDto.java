package com.who.userservice.dto;

import jakarta.validation.Valid;

public record CreateContactRequestDto(
        @Valid
        ContactDto contactDto,
        @Valid
        UserDto userDto
) {
}
