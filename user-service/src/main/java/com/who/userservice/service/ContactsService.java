package com.who.userservice.service;

import com.who.userservice.dto.ContactsResponseDto;
import com.who.userservice.entity.Contact;
import com.who.userservice.entity.ContactCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ContactsService {
    List<ContactsResponseDto> getAllContacts(UUID userId, Boolean isBlocked, ContactCategory contactCategory);
    Contact findContactByContactIdAndUserId(UUID userId, UUID contactId);
    ContactsResponseDto getContactByContactIdAndUserId(UUID userId, UUID contactId);
    ContactsResponseDto updateContactCategory(UUID userId, UUID contactId, ContactCategory contactCategory);
    ContactsResponseDto blockOrUnblockContact(UUID userId, UUID contactId, Boolean isBlocked);
    ContactsResponseDto updateContactName(UUID userId, UUID contactId, String name);
    ContactsResponseDto updateContact(Contact contact);
    void deleteContact(UUID userId, UUID contactId);

}
