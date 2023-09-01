package com.who.messageservice.service;

import com.who.messageservice.dto.RecipientDto;
import com.who.messageservice.entity.Recipient;

import java.util.UUID;

public interface RecipientService {
    Recipient createRecipient(RecipientDto recipientDto);
    Recipient getRecipientById(UUID id);
    Recipient deleteRecipient(UUID id);
}
