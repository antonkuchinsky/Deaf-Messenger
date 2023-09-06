package com.who.messageservice.service.impl;

import com.who.messageservice.dto.ChatDto;
import com.who.messageservice.entity.Chat;
import com.who.messageservice.entity.Message;
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
    public Chat createChat(ChatDto chatDto) {
        boolean chatExists = chatRepository.existsChatByCommunity(chatDto.recipients());
        if (chatExists) {
            throw new InvalidDataException("You already have a chat with these users or user",
                    "This chat can't be created");
        }
        Chat chat = new Chat();
        chat.setNameChat(chatDto.nameChat());
        chat.setCommunity(chatDto.recipients());
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
        return chatRepository.findChatByCommunityId(recipientId).
                orElseThrow(()->new InvalidDataException("This user haven't chats",
                        "Сhats not found"));
    }

    @Override
    public List<Message> getAllMessageByChat(Chat chat) {
        return chatRepository.findById(chat.getId()).get().getChatMessages();
    }

    @Override
    public Chat updateChat(Chat chat) {
        var entityChat=chatRepository.findById(chat.getId());
        entityChat.get().setNameChat(chat.getNameChat());
        entityChat.get().setCommunity(chat.getCommunity());
        entityChat.get().setChatMessages(chat.getChatMessages());
        return chatRepository.save(entityChat.get());
    }

    @Override
    public Chat updateChatMessages(Chat chat,Message message) {
        List<Message> messages=chat.getChatMessages();
        messages.add(message);
        chat.setChatMessages(messages);
        return chatRepository.save(chat);
    }

    @Override
    public void deleteChat(UUID chatId) {
        chatRepository.deleteById(chatId);
    }
}
