package com.who.messageservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.who.messageservice.dto.MessageDto;
import com.who.messageservice.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    void sendMessage(MessageDto messageDto) throws JsonProcessingException;
    List<Message> getExistingChatMessages(UUID chatId);
}
