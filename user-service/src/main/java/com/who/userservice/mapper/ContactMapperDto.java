package com.who.userservice.mapper;

import com.who.userservice.dto.ContactsResponseDto;
import com.who.userservice.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ContactMapperDto implements Function<Contact, ContactsResponseDto> {
    @Override
    public ContactsResponseDto apply(Contact contact) {
        return new ContactsResponseDto(
                contact.getContact().getId(),
                contact.getContactName(),
                contact.getContact().getIsActive(),
                contact.getContact().getLastActive(),
                contact.getContactCategory(),
                contact.getIsBlocked()
        );
    }
}
