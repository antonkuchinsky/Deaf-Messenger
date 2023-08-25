package com.who.userservice.service;

import com.who.userservice.entity.Contact;
import com.who.userservice.entity.ContactCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ContactsService {
    List<Contact> getAllContacts(UUID userId, Boolean isBlocked, ContactCategory contactCategory);
    Contact getContactByContactIdAndUserId(UUID userId, UUID contactId);
    Contact editContactCategory(Contact contact, ContactCategory contactCategory);
    Contact blockOrUnblockContact(Contact contact, Boolean isBlocked);
}
