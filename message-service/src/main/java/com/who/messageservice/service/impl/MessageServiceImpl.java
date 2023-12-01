package com.who.messageservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.who.messageservice.config.kafka.Producer;
import com.who.messageservice.dto.ChatNotificationDto;
import com.who.messageservice.dto.MessageDto;
import com.who.messageservice.entity.Message;
import com.who.messageservice.mapper.MessageMapperDto;
import com.who.messageservice.repository.ChatRepository;
import com.who.messageservice.repository.MessageRepository;
import com.who.messageservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final SimpMessagingTemplate messagingTemplate;
    private final Producer producer;
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final MessageMapperDto messageMapperDto;
    private final int MAX_PAGABLE_MESSAGES = 100;


    @Override
    public void sendMessage(MessageDto messageDto) throws JsonProcessingException {
        messageRepository.save(messageMapperDto.apply(messageDto));
        messagingTemplate.convertAndSend("/topic/private.chat." + messageDto.chatId(), messageDto);
        producer.sendMessage(new ChatNotificationDto(messageDto.senderId(),messageDto.recipientId()));
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
