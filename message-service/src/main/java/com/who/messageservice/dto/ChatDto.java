package com.who.messageservice.dto;

import java.util.UUID;

public record ChatDto(
        UUID chatId,
        UUID userIdOne,
        UUID userIdTwo
) {
}
