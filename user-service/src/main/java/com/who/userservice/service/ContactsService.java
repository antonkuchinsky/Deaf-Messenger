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
    ContactsResponseDto updateContactCategory(Contact contact, ContactCategory contactCategory);
    ContactsResponseDto blockOrUnblockContact(Contact contact, Boolean isBlocked);
    ContactsResponseDto updateContactName(Contact contact, String name);
    ContactsResponseDto saveContact(Contact contact);


}
