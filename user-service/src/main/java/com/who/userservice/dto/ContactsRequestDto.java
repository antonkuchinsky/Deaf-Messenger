package com.who.userservice.dto;

import com.who.userservice.entity.ContactCategory;

public record ContactsRequestDto(
        ContactCategory contactCategory,
        Boolean isBlocked,
        String username
){
}
