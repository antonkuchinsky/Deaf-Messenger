package com.who.messageservice.dto;

import java.util.UUID;

public record ChatNotificationDto(
        UUID senderId,
        UUID recepientId
) {
}
