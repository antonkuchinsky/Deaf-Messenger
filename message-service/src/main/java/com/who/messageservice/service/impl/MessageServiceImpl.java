package com.who.messageservice.service.impl;

import com.who.messageservice.dto.MessageDto;
import com.who.messageservice.entity.Chat;
import com.who.messageservice.entity.Message;
import com.who.messageservice.exception.InvalidDataException;
import com.who.messageservice.mapper.MessageMapperDto;
import com.who.messageservice.repository.ChatRepository;
import com.who.messageservice.repository.MessageRepository;
import com.who.messageservice.service.MessageService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final MessageMapperDto messageMapperDto;
    private final int MAX_PAGABLE_MESSAGES = 100;

    public MessageServiceImpl(MessageRepository messageRepository, ChatRepository chatRepository, MessageMapperDto messageMapperDto) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.messageMapperDto = messageMapperDto;
    }


    @Override
    public void sendMessage(MessageDto messageDto) {
        messageRepository.save(messageMapperDto.apply(messageDto));
        //implement ChatNotification
    }

    @Override
    public List<Message> getExistingChatMessages(UUID chatId) {
        var chat=chatRepository.findById(chatId);
        List<Message> chatMessages =
                messageRepository.getExistingChatMessages(
                        chat.get().getUserIdOne(),
                        chat.get().getUserIdTwo(),
                        PageRequest.of(0,MAX_PAGABLE_MESSAGES)
                );
        return chatMessages;
    }

}
