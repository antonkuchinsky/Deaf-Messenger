package com.who.userservice.service.impl;

import com.who.userservice.dto.ContactsResponseDto;
import com.who.userservice.entity.Contact;
import com.who.userservice.entity.ContactCategory;
import com.who.userservice.exception.InvalidDataException;
import com.who.userservice.mapper.ContactMapperDto;
import com.who.userservice.repository.ContactsRepository;
import com.who.userservice.service.ContactsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactsServiceImpl implements ContactsService {
    private final ContactsRepository contactsRepository;
    private final ContactMapperDto contactMapperDto;

    public ContactsServiceImpl(ContactsRepository contactsRepository, ContactMapperDto contactMapperDto) {
        this.contactsRepository = contactsRepository;
        this.contactMapperDto = contactMapperDto;
    }

    @Override
    public List<ContactsResponseDto> getAllContacts(UUID userId, Boolean isBlocked, ContactCategory contactCategory) {
        return contactsRepository.getAllContactByUserIdAndIsBlocked(userId, isBlocked, contactCategory)
                .stream()
                .map(contactMapperDto)
                .toList();
    }

    @Override
    @Transactional
    public ContactsResponseDto saveContact(Contact contact) {
        return Optional.of(contactsRepository.save(contact))
                .map(contactMapperDto).orElseThrow();
    }

    @Override
    public Contact findContactByContactIdAndUserId(UUID userId, UUID contactId) {
        return contactsRepository.getContactsByUserID(userId, contactId)
                .orElseThrow(() -> new InvalidDataException("Contact with id " + contactId +
                        " is not in the database", "Contact not found"));
    }

    @Override
    public ContactsResponseDto getContactByContactIdAndUserId(UUID userId, UUID contactId) {
        return Optional.ofNullable(findContactByContactIdAndUserId(userId, contactId))
                .map(contactMapperDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public ContactsResponseDto updateContactCategory(@NotNull Contact contact, ContactCategory contactCategory) {
        contact.setContactCategory(contactCategory);
        return saveContact(contact);
    }

    @Override
    @Transactional
    public ContactsResponseDto blockOrUnblockContact(@NotNull Contact contact, Boolean isBlocked) {
        contact.setIsBlocked(isBlocked);
        return saveContact(contact);
    }

    @Override
    @Transactional
    public ContactsResponseDto updateContactName(@NotNull Contact contact, String name) {
        contact.setContactName(name);
        return saveContact(contact);
    }

}
