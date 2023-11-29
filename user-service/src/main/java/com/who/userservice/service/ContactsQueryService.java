package com.who.userservice.service;
import com.who.userservice.dto.ContactsResponseDto;
import com.who.userservice.entity.Contact;
import com.who.userservice.entity.ContactCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;


import java.util.UUID;

public interface ContactsQueryService {
    Slice<ContactsResponseDto> getAllContacts(UUID userId, Boolean isBlocked, ContactCategory contactCategory, Pageable pageable);
    Contact findContactByContactIdAndUserId(UUID userId, UUID contactId);
    ContactsResponseDto getContactByContactIdAndUserId(UUID userId, UUID contactId);
}
