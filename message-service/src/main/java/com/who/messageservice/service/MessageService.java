package com.who.messageservice.service;

import com.who.messageservice.dto.MessageDto;
import com.who.messageservice.entity.Message;

import java.util.UUID;

public interface MessageService {
    Message sendMessage(Message message);
    Message getMessageById(UUID messageId);
    Message updateMessage(UUID messageId, MessageDto messageDto);
    void deleteMessage(UUID messageId);
}
