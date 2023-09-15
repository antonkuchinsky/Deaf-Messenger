package com.who.messageservice.dto;

import java.util.UUID;

public record MessageDto(
        UUID senderId,
        UUID recipientId,
        UUID chatId,
        String text
) {
}
