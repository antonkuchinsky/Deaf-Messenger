package com.who.messageservice.service.impl;

import com.who.messageservice.dto.ChatInitializationDTO;
import com.who.messageservice.entity.Chat;
import com.who.messageservice.repository.ChatRepository;
import com.who.messageservice.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    @Override
    public Chat getExistingChannel(ChatInitializationDTO chatInitializationDTO) {
        List<Chat> chats = chatRepository.findExistingChannel(chatInitializationDTO.userIdOne(), chatInitializationDTO.userIdTwo());
        return (chats != null && !chats.isEmpty()) ? chats.get(0) : null;
    }

    @Override
    public Chat newChatSession(ChatInitializationDTO chatInitializationDTO) {
        var chat = Chat.builder()
                .userIdOne(chatInitializationDTO.userIdOne())
                .userIdTwo(chatInitializationDTO.userIdTwo())
                .build();
        return chatRepository.save(chat);
    }

    @Override
    public Chat establishChatSession(ChatInitializationDTO chatInitializationDTO) {
        var chat = getExistingChannel(chatInitializationDTO);
        return (chat != null) ? chat : newChatSession(chatInitializationDTO);
    }
}
