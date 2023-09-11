package com.who.messageservice.service;

import com.who.messageservice.dto.ChatDto;
import com.who.messageservice.entity.Chat;

import java.util.List;
import java.util.UUID;

public interface ChatService {
    Chat createGroupChat(ChatDto chatDto);
    Chat getChatById(UUID chatId);
    List<Chat> getAllChatById(UUID userId);
    void deleteChat(UUID chatId);
}
