package com.who.userservice.service;

import com.who.userservice.dto.ContactsResponseDto;

import java.util.UUID;

public interface ContactsModificationService  extends ContactsModificationUpdateService{
    ContactsResponseDto blockOrUnblockContact(UUID userId, UUID contactId, Boolean isBlocked);
    void deleteContact(UUID userId, UUID contactId);
}