package com.who.userservice.service;

import com.who.userservice.entity.Contact;
import com.who.userservice.entity.ContactCategory;
import com.who.userservice.exception.InvalidDataException;
import com.who.userservice.repository.ContactsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactsServiceImpl implements ContactsService {
    private final ContactsRepository contactsRepository;

    public ContactsServiceImpl(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @Override
    public List<Contact> getAllContacts(UUID userId, Boolean isBlocked, ContactCategory contactCategory) {
        return contactsRepository.getAllContactByUserIdAndIsBlocked(userId, isBlocked, contactCategory);
    }

    @Override
    public Contact getContactByContactIdAndUserId(UUID userId, UUID contactId) {
        return contactsRepository.getContactsByUserID(userId, contactId)
                .orElseThrow(() -> new InvalidDataException("Contact with id " + contactId +
                        " is not in the database", "Contact not found"));
    }

    @Override
    public Contact editContactCategory(Contact contact, ContactCategory contactCategory) {
        contact.setContactCategory(contactCategory);
        return contactsRepository.save(contact);
    }

    @Override
    public Contact blockOrUnblockContact(Contact contact, Boolean isBlocked) {
        contact.setIsBlocked(isBlocked);
        return contactsRepository.save(contact);
    }

}
