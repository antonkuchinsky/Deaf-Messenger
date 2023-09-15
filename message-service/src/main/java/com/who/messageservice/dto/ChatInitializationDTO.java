package com.who.messageservice.dto;

import java.util.UUID;

public record ChatInitializationDTO(
        UUID userIdOne,
        UUID userIdTwo
) {
}
