package com.who.userservice.controller;
import com.who.userservice.dto.ContactsRequestDto;
import com.who.userservice.dto.ContactsResponseDto;
import com.who.userservice.entity.ContactCategory;
import com.who.userservice.service.ContactsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/{user_id}/contacts")
public class ContactsController {
    private final ContactsService contactsService;

    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @GetMapping
    public Slice<ContactsResponseDto> getAllContacts(@PathVariable("user_id") UUID id,
                                                     @RequestParam(value = "is_blocked", defaultValue = "false") Boolean isBlocked,
                                                     @RequestParam(value = "contact_category", required = false) ContactCategory contactCategory,
                                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {

        return contactsService.getAllContacts(id, isBlocked, contactCategory, PageRequest.of(page, size));
    }

    @GetMapping("/{contact_id}")
    public ContactsResponseDto getUserContactByContactId(@PathVariable("user_id") UUID userId,
                                                         @PathVariable("contact_id") UUID contactId) {
        return contactsService.getContactByContactIdAndUserId(userId, contactId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{contact_id}")
    public void deleteContact(@PathVariable("user_id") UUID userId,
                              @PathVariable("contact_id") UUID contactId) {
        contactsService.deleteContact(userId, contactId);
    }

    @PatchMapping("/{contact_id}/category/edit")
    public ContactsResponseDto updateContactCategory(@PathVariable("user_id") UUID userId,
                                                     @PathVariable("contact_id") UUID contactId,
                                                     @RequestBody @NotNull @Valid ContactsRequestDto contactsRequestDto) {
        return contactsService.updateContactCategory(userId, contactId, contactsRequestDto.contactCategory());
    }

    @PatchMapping("/{contact_id}/name/edit")
    public ContactsResponseDto updateContactName(@PathVariable("user_id") UUID userId,
                                                 @PathVariable("contact_id") UUID contactId,
                                                 @RequestBody @NotNull @Valid ContactsRequestDto contactsRequestDto) {

        return contactsService.updateContactName(userId, contactId, contactsRequestDto.username());
    }

    @PatchMapping("/{contact_id}/block")
    public ContactsResponseDto updateContactsBlock(@PathVariable("user_id") UUID userId,
                                                   @PathVariable("contact_id") UUID contactId,
                                                   @RequestBody @NotNull @Valid ContactsRequestDto contactsRequestDto) {

        return contactsService.blockOrUnblockContact(userId, contactId, contactsRequestDto.isBlocked());
    }


}
