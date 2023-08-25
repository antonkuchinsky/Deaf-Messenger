package com.who.userservice.dto;

import com.who.userservice.entity.ContactCategory;

import java.time.ZonedDateTime;
import java.util.UUID;

public record ContactsDto (
        UUID contactsId,
        String contactsName,
        Boolean isActive,
        ZonedDateTime lastActive,
        ContactCategory contactCategory,
        Boolean isBlocked
){
}
