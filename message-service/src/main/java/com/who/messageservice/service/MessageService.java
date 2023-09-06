package com.who.messageservice.service;

import com.who.messageservice.dto.MessageDto;
import com.who.messageservice.entity.Chat;
import com.who.messageservice.entity.Message;

import java.util.UUID;

public interface MessageService {
    Message createMessage(MessageDto messageDto, Chat chat, UUID senderId);
    Message getMessageById(UUID messageId);
    Message updateMessage(UUID messageId, MessageDto messageDto);
    void deleteMessage(UUID messageId);
}
