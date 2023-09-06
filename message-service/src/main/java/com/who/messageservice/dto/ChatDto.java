package com.who.messageservice.dto;

import com.who.messageservice.entity.Message;
import com.who.messageservice.entity.Recipient;

import java.util.List;

public record ChatDto(
        String nameChat,
        List<Recipient>recipients,
        List<Message> chatMessages
) {
}
