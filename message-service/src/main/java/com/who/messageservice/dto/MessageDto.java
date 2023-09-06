package com.who.messageservice.dto;

import com.who.messageservice.entity.Recipient;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public record MessageDto(
        UUID senderId,
        String text,
        List<Recipient> recipients,
        ZonedDateTime dateTimeSendingMessage
) {
}
