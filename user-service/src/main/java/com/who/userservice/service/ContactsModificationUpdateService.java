package com.who.userservice.service;

import com.who.userservice.dto.ContactsResponseDto;
import com.who.userservice.entity.Contact;
import com.who.userservice.entity.ContactCategory;

import java.util.UUID;

public interface ContactsModificationUpdateService {
    ContactsResponseDto updateContactCategory(UUID userId, UUID contactId, ContactCategory contactCategory);
    ContactsResponseDto updateContactName(UUID userId, UUID contactId, String name);
    ContactsResponseDto updateContact(Contact contact);
}
