package com.who.messageservice.service;

import com.who.messageservice.dto.ChatDto;
import com.who.messageservice.entity.Chat;
import com.who.messageservice.entity.Message;

import java.util.List;
import java.util.UUID;

public interface ChatService {
    Chat createChat(ChatDto chatDto);
    Chat getChatById(UUID chatId);
    List<Chat> getChatsByUserId(UUID recipientId);
    List<Message> getAllMessageByChat(Chat chat);
    Chat updateChat(Chat chat);
    Chat updateChatMessages(Chat chat,Message message);
    void deleteChat(UUID chatId);
}
