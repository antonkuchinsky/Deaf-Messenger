package com.who.userservice.controller;

import com.who.userservice.dto.ContactsDto;
import com.who.userservice.entity.Contact;
import com.who.userservice.entity.ContactCategory;
import com.who.userservice.entity.User;
import com.who.userservice.service.ContactsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final ContactsService contactsService;

    public UserController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @GetMapping("/{user_id}")
    public User getUser(@PathVariable("user_id") UUID id) {
        return null;
    }


    @GetMapping("/{user_id}/contacts")
    public List<Contact> getAllContacts(@PathVariable("user_id") UUID id,
                                        @RequestParam(value = "is_blocked", defaultValue = "false") Boolean isBlocked,
                                        @RequestParam(value = "contact_category", required = false) ContactCategory contactCategory) {
        return contactsService.getAllContacts(id, isBlocked, contactCategory);
    }

    @GetMapping("/{user_id}/contacts/{contact_id}")
    public Contact getAllContacts(@PathVariable("user_id") UUID userId,
                                  @PathVariable("contact_id") UUID contactId) {
        return contactsService.getContactByContactIdAndUserId(userId, contactId);
    }

    @PatchMapping("/{user_id}/contacts/{contact_id}/category/edit")
    public Contact editContactCategory(@PathVariable("user_id") UUID userId,
                                    @PathVariable("contact_id") UUID contactId,
                                    @RequestBody ContactsDto contactsDto) {
        var contact = contactsService.getContactByContactIdAndUserId(userId, contactId);
        return contactsService.editContactCategory(contact, contactsDto.contactCategory());
    }

    @PatchMapping("/{user_id}/contacts/{contact_id}/block")
    public Contact blockContact(@PathVariable("user_id") UUID userId,
                             @PathVariable("contact_id") UUID contactId,
                             @RequestBody ContactsDto contactsDto) {
        var contact = contactsService.getContactByContactIdAndUserId(userId, contactId);
        return contactsService.blockOrUnblockContact(contact, contactsDto.isBlocked());
    }



}
