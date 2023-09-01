package com.who.messageservice.service;

import com.who.messageservice.dto.ChatDto;
import com.who.messageservice.entity.Chat;

import java.util.List;
import java.util.UUID;

public interface ChatService {
    Chat createChat(Chat chat);
    Chat getChatById(UUID chatId);
    List<Chat> getChatsByUserId(UUID recipientId);
    Chat updateChat(UUID id, ChatDto chatDto);
    void deleteChat(UUID chatId);
}
