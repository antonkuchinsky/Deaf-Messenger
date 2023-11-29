package com.who.userservice.contact;

import com.who.userservice.dto.ContactsResponseDto;
import com.who.userservice.entity.Contact;
import com.who.userservice.entity.ContactCategory;
import com.who.userservice.entity.User;
import com.who.userservice.exception.InvalidDataException;
import com.who.userservice.mapper.ContactMapperDto;
import com.who.userservice.repository.ContactsRepository;
import com.who.userservice.service.impl.ContactsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;


import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContactServiceTest {
    @Mock
    private ContactsRepository contactsRepository;

    @Mock
    private ContactMapperDto contactMapperDto;

    @InjectMocks
    private ContactsServiceImpl contactsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllContactsShouldReturnContactsList() {
        UUID userId = UUID.randomUUID();
        Boolean isBlocked = false;
        ContactCategory contactCategory = ContactCategory.FRIEND;

        List<Contact> contacts = new ArrayList<>();
        Contact contact1 = new Contact();
        contact1.setContact(new User());
        contact1.setContactName("contact1");
        contact1.getContact().setIsActive(true);
        contact1.getContact().setLastActive(ZonedDateTime.now());
        contact1.setContactCategory(contactCategory);
        contact1.setIsBlocked(isBlocked);
        contacts.add(contact1);

        Slice<ContactsResponseDto> expectedSlice = new SliceImpl<>(Collections.singletonList(
                new ContactsResponseDto(
                        contact1.getContact().getId(),
                        contact1.getContactName(),
                        contact1.getContact().getIsActive(),
                        contact1.getContact().getLastActive(),
                        contactCategory,
                        contact1.getIsBlocked()
                )
        ));

        //when(contactsRepository.getAllContactByUserIdAndIsBlocked(userId, isBlocked, contactCategory, PageRequest.of(0, 10)))
                //.thenReturn(expectedSlice);

        Slice<ContactsResponseDto> result = contactsService.getAllContacts(userId, isBlocked, contactCategory, PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getContent().size());

        ContactsResponseDto resultDto = result.getContent().get(0);
        assertEquals(contact1.getContact().getId(), resultDto.contactsId());
        assertEquals(contact1.getContactName(), resultDto.contactsName());
        assertEquals(contact1.getContact().getIsActive(), resultDto.isActive());
        assertEquals(contact1.getContact().getLastActive(), resultDto.lastActive());
        assertEquals(contact1.getContactCategory(), resultDto.contactCategory());
        assertEquals(contact1.getIsBlocked(), resultDto.isBlocked());

        verify(contactsRepository, times(1))
                .getAllContactByUserIdAndIsBlocked(userId, isBlocked, contactCategory, PageRequest.of(0, 10));
    }

    @Test
    public void updateContactShouldReturnUpdatedContact() {
        Contact contact = new Contact();
        contact.setContact(new User());
        contact.setContactName("contact1");
        contact.getContact().setIsActive(true);
        contact.getContact().setLastActive(ZonedDateTime.now());
        contact.setContactCategory(ContactCategory.FRIEND);
        contact.setIsBlocked(false);

        when(contactMapperDto.apply(contact)).thenReturn(
                new ContactsResponseDto(
                        contact.getContact().getId(),
                        contact.getContactName(),
                        contact.getContact().getIsActive(),
                        contact.getContact().getLastActive(),
                        contact.getContactCategory(),
                        contact.getIsBlocked()
                ));

        ContactsResponseDto result = contactsService.updateContact(contact);

        assertNotNull(result);
        assertEquals(contact.getContact().getId(), result.contactsId());
        assertEquals(contact.getContactName(), result.contactsName());
        assertEquals(contact.getContact().getIsActive(), result.isActive());
        assertEquals(contact.getContact().getLastActive(), result.lastActive());
        assertEquals(contact.getContactCategory(), result.contactCategory());
        assertEquals(contact.getIsBlocked(), result.isBlocked());

        verify(contactMapperDto, times(1)).apply(contact);
    }

    @Test
    public void updateContactShouldReturnUpdatedContactDto() {
        Contact contact = new Contact();
        contact.setContact(new User());
        contact.setContactName("contact1");
        contact.getContact().setIsActive(true);
        contact.getContact().setLastActive(ZonedDateTime.now());
        contact.setContactCategory(ContactCategory.FRIEND);
        contact.setIsBlocked(false);

        when(contactMapperDto.apply(contact)).thenReturn(
                new ContactsResponseDto(
                        contact.getContact().getId(),
                        contact.getContactName(),
                        contact.getContact().getIsActive(),
                        contact.getContact().getLastActive(),
                        contact.getContactCategory(),
                        contact.getIsBlocked()
                )
        );

        ContactsResponseDto result = contactsService.updateContact(contact);

        assertNotNull(result);
        assertEquals(contact.getContact().getId(), result.contactsId());
        assertEquals(contact.getContactName(), result.contactsName());
        assertEquals(contact.getContact().getIsActive(), result.isActive());
        assertEquals(contact.getContact().getLastActive(), result.lastActive());
        assertEquals(contact.getContactCategory(), result.contactCategory());
        assertEquals(contact.getIsBlocked(), result.isBlocked());

        verify(contactMapperDto, times(1)).apply(contact);
    }

    @Test
    public void findContactByContactIdAndUserIdContactFoundShouldReturnContact() {
        UUID userId = UUID.randomUUID();
        UUID contactId = UUID.randomUUID();

        Contact contact = new Contact();
        contact.setContact(new User());
        contact.setContactName("contact1");
        contact.getContact().setIsActive(true);
        contact.getContact().setLastActive(ZonedDateTime.now());
        contact.setContactCategory(ContactCategory.FRIEND);
        contact.setIsBlocked(false);

        when(contactsRepository.getContactsByUserID(userId, contactId)).thenReturn(Optional.of(contact));

        Contact result = contactsService.findContactByContactIdAndUserId(userId, contactId);

        assertNotNull(result);
        assertEquals(contact, result);

        verify(contactsRepository, times(1)).getContactsByUserID(userId, contactId);
    }

    @Test
    public void findContactByContactIdAndUserIdContactNotFoundShouldThrowInvalidDataException() {
        UUID userId = UUID.randomUUID();
        UUID contactId = UUID.randomUUID();

        when(contactsRepository.getContactsByUserID(userId, contactId)).thenReturn(Optional.empty());

        assertThrows(InvalidDataException.class, () -> contactsService.findContactByContactIdAndUserId(userId, contactId));

        verify(contactsRepository, times(1)).getContactsByUserID(userId, contactId);
    }
}
