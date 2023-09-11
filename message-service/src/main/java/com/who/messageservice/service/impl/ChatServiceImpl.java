package com.who.messageservice.service.impl;

import com.who.messageservice.dto.ChatDto;
import com.who.messageservice.entity.Chat;
import com.who.messageservice.exception.InvalidDataException;
import com.who.messageservice.repository.ChatRepository;
import com.who.messageservice.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    @Override
    public Chat createGroupChat(ChatDto chatDto) {
        var chat= Chat.builder()
                .nameChat(chatDto.nameChat())
                .community(chatDto.recipients())
                .build();
        return chatRepository.save(chat);
    }

    @Override
    public Chat getChatById(UUID chatId) {
        return chatRepository.findById(chatId).
                orElseThrow(() -> new InvalidDataException("This chat not exist",
                        "Chat not found"));
    }

    @Override
    public List<Chat> getAllChatById(UUID userId) {
        return chatRepository.findChatsByRecipientId(userId);
    }


    @Override
    public void deleteChat(UUID chatId) {
        chatRepository.deleteById(chatId);
    }
}
