package com.who.messageservice.service;

import com.who.messageservice.dto.ChatDto;
import com.who.messageservice.entity.Recipient;

import java.util.List;
import java.util.UUID;

public interface RecipientService {
    Recipient createRecipient(Recipient recipient);
    Recipient getRecipientById(UUID id);
    Recipient deleteRecipient(UUID id);
    List<Recipient> getAllRecipientsByChat(ChatDto chatDto);
}
