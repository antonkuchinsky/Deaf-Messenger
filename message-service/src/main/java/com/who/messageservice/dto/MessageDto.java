package com.who.messageservice.dto;


import java.time.ZonedDateTime;
import java.util.UUID;

public record MessageDto(
        UUID senderId,
        UUID chatId,
        String text,
        ZonedDateTime dateTimeSendingMessage
) {
}
