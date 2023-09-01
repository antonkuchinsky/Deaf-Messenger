package com.who.messageservice.service.impl;

import com.who.messageservice.dto.RecipientDto;
import com.who.messageservice.entity.Recipient;
import com.who.messageservice.service.RecipientService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RecipientServiceImpl implements RecipientService {
    @Override
    public Recipient createRecipient(RecipientDto recipientDto) {
        return null;
    }

    @Override
    public Recipient getRecipientById(UUID id) {
        return null;
    }

    @Override
    public Recipient deleteRecipient(UUID id) {
        return null;
    }
}
