package com.who.messageservice.service;

import com.who.messageservice.dto.MessageDto;
import com.who.messageservice.entity.Message;

import java.util.UUID;

public interface MessageService {
    Message createMessage(MessageDto messageDto);
    Message getMessageById(UUID messageId);
    Message getMessagesBySenderId(UUID senderId);
    Message getMessagesByRecipientId(UUID recipientId);
    Message updateMessage(UUID messageId, MessageDto messageDto);
    Message deleteMessage(UUID messageId);
}
