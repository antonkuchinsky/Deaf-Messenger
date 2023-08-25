package com.who.userservice.dto;

import com.who.userservice.entity.ContactCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record ContactsRequestDto(
        ContactCategory contactCategory,
        Boolean isBlocked,
        @NotNull(message = "Username is not null")
        @Pattern(message = "Bad formed person username: ${validatedValue}",
                regexp = "^[a-z A-Z0-9_-]{2,255}$")
        @Length(min = 2, max = 255, message = "The length of the name must be from 2 to 255 characters")
        String username
){
}
