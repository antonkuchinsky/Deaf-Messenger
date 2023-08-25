package com.who.userservice.controller;

import com.who.userservice.dto.ContactsRequestDto;
import com.who.userservice.dto.ContactsResponseDto;
import com.who.userservice.entity.ContactCategory;
import com.who.userservice.entity.User;
import com.who.userservice.service.ContactsService;
import com.who.userservice.service.UserService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final ContactsService contactsService;
    private final UserService userService;

    public UserController(ContactsService contactsService, UserService userService) {
        this.contactsService = contactsService;
        this.userService = userService;
    }

    @GetMapping("/{user_id}")
    public User getUser(@PathVariable("user_id") UUID id) {
        return userService.getUserById(id);
    }

    @PatchMapping("/{user_id}")
    public User updateUsername(@PathVariable("user_id") UUID id,
                               @RequestBody @NotNull ContactsRequestDto contactsRequestDto) {
        var user = userService.getUserById(id);
        return userService.updateUsername(user, contactsRequestDto.username());
    }


    @GetMapping("/{user_id}/contacts")
    public List<ContactsResponseDto> getAllContacts(@PathVariable("user_id") UUID id,
                                                    @RequestParam(value = "is_blocked", defaultValue = "false") Boolean isBlocked,
                                                    @RequestParam(value = "contact_category", required = false) ContactCategory contactCategory) {
        return contactsService.getAllContacts(id, isBlocked, contactCategory);
    }

    @GetMapping("/{user_id}/contacts/{contact_id}")
    public ContactsResponseDto getAllContacts(@PathVariable("user_id") UUID userId,
                                              @PathVariable("contact_id") UUID contactId) {
        return contactsService.getContactByContactIdAndUserId(userId, contactId);
    }

    @PatchMapping("/{user_id}/contacts/{contact_id}/category/edit")
    public ContactsResponseDto updateContactCategory(@PathVariable("user_id") UUID userId,
                                                     @PathVariable("contact_id") UUID contactId,
                                                     @RequestBody @NotNull @Valid ContactsRequestDto contactsRequestDto) {
        var contact = contactsService.findContactByContactIdAndUserId(userId, contactId);
        return contactsService.updateContactCategory(contact, contactsRequestDto.contactCategory());
    }

    @PatchMapping("/{user_id}/contacts/{contact_id}/name/edit")
    public ContactsResponseDto updateContactName(@PathVariable("user_id") UUID userId,
                                                 @PathVariable("contact_id") UUID contactId,
                                                 @RequestBody @NotNull @Valid ContactsRequestDto contactsRequestDto) {
        var contact = contactsService.findContactByContactIdAndUserId(userId, contactId);
        return contactsService.updateContactName(contact, contactsRequestDto.username());
    }

    @PatchMapping("/{user_id}/contacts/{contact_id}/block")
    public ContactsResponseDto updateContactsBlock(@PathVariable("user_id") UUID userId,
                                                   @PathVariable("contact_id") UUID contactId,
                                                   @RequestBody @NotNull @Valid ContactsRequestDto contactsRequestDto) {
        var contact = contactsService.findContactByContactIdAndUserId(userId, contactId);
        return contactsService.blockOrUnblockContact(contact, contactsRequestDto.isBlocked());
    }


}
