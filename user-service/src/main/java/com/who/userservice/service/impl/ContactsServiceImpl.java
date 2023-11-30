package com.who.userservice.service.impl;

import com.who.userservice.dto.ContactsResponseDto;
import com.who.userservice.entity.Contact;
import com.who.userservice.entity.ContactCategory;
import com.who.userservice.exception.InvalidDataException;
import com.who.userservice.mapper.ContactMapperDto;
import com.who.userservice.repository.ContactsRepository;
import com.who.userservice.service.ContactsService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Slice<ContactsResponseDto> getAllContacts(UUID userId,
                                                     Boolean isBlocked,
                                                     ContactCategory contactCategory,
                                                     Pageable pageable) {

        return contactsRepository.getAllContactByUserIdAndIsBlocked
                        (userId, isBlocked, contactCategory, pageable)
                .map(contactMapperDto);
    }

    public ContactsResponseDto updateContact(Contact contact) {
        return Optional.of(contact)
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
    public ContactsResponseDto updateContactCategory(UUID userId, UUID contactId, ContactCategory contactCategory) {
        var contact = findContactByContactIdAndUserId(userId, contactId);
        contact.setContactCategory(contactCategory);
        return updateContact(contact);
    }

    @Override
    @Transactional
    public ContactsResponseDto blockOrUnblockContact(UUID userId, UUID contactId, Boolean isBlocked) {
        var contact = findContactByContactIdAndUserId(userId, contactId);
        contact.setIsBlocked(isBlocked);
        return updateContact(contact);
    }

    @Override
    @Transactional
    public ContactsResponseDto updateContactName(UUID userId, UUID contactId, String name) {
        var contact = findContactByContactIdAndUserId(userId, contactId);
        contact.setContactName(name);
        return updateContact(contact);
    }

    @Override
    @Transactional
    public void deleteContact(UUID userId, UUID contactId) {
        contactsRepository.deleteContactByIdAndUserId(userId, contactId);
    }

}
