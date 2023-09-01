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
    public Chat createChat(Chat chat) {
        if (chat.getCommunity() == null || chat.getCommunity().isEmpty()) {
            boolean chatExists = chatRepository.existsByChatMessagesSenderIn(chat.getChatMessages().get(0).getSenderId());
            if (chatExists) {
                throw new InvalidDataException("You already have a chat with this user",
                        "This chat can't be create");
            }
        }
        return chatRepository.save(chat);
    }

    @Override
    public Chat getChatById(UUID chatId) {
        return chatRepository.findById(chatId).
                orElseThrow(() -> new InvalidDataException("This chat not exist",
                        "Chat not found"));
    }

    @Override
    public List<Chat> getChatsByUserId(UUID recipientId) {
        return chatRepository.findChatByCommunity(recipientId).
                orElseThrow(()->new InvalidDataException("This user haven't chats",
                        "Сhats not found"));
    }

    @Override
    public Chat updateChat(UUID id, ChatDto chatDto) {
        var entityChat=chatRepository.findById(id);
        entityChat.get().setNameChat(chatDto.nameChat());
        return chatRepository.save(entityChat.get());
    }

    @Override
    public void deleteChat(UUID chatId) {
        chatRepository.deleteById(chatId);
    }
}
