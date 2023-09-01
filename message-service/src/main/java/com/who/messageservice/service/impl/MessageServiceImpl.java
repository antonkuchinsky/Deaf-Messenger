package com.who.messageservice.service.impl;

import com.who.messageservice.dto.MessageDto;
import com.who.messageservice.entity.Message;
import com.who.messageservice.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public Message createMessage(MessageDto messageDto) {
        return null;
    }

    @Override
    public Message getMessageById(UUID messageId) {
        return null;
    }

    @Override
    public Message getMessagesBySenderId(UUID senderId) {
        return null;
    }

    @Override
    public Message getMessagesByRecipientId(UUID recipientId) {
        return null;
    }

    @Override
    public Message updateMessage(UUID messageId, MessageDto messageDto) {
        return null;
    }

    @Override
    public Message deleteMessage(UUID messageId) {
        return null;
    }
}
