package com.who.messageservice.service.impl;

import com.who.messageservice.dto.MessageDto;
import com.who.messageservice.entity.Chat;
import com.who.messageservice.entity.Message;
import com.who.messageservice.exception.InvalidDataException;
import com.who.messageservice.repository.MessageRepository;
import com.who.messageservice.service.ChatService;
import com.who.messageservice.service.MessageService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatService chatService;

    public MessageServiceImpl(MessageRepository messageRepository, ChatService chatService) {
        this.messageRepository = messageRepository;
        this.chatService = chatService;
    }

    @Override
    public Message createMessage(MessageDto messageDto, Chat chat, UUID senderId) {
        var message=new Message();
        message.setSenderId(senderId);
        message.setTextMessage(messageDto.text());
        message.setDateTimeSendingMessage(ZonedDateTime.now());
        message.setRecipients(chat.getCommunity());
        chatService.updateChatMessages(chat,message);
        return messageRepository.save(message);
    }


    @Override
    public Message getMessageById(UUID messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(()-> new InvalidDataException("This message not found",
                        "Message not found"));
    }



    @Override
    public Message updateMessage(UUID messageId, MessageDto messageDto) {
        var message=messageRepository.findById(messageId);
        message.get().setTextMessage(messageDto.text());
        return messageRepository.save(message.get());
    }

    @Override
    public void deleteMessage(UUID messageId) {
        messageRepository.deleteById(messageId);
    }
}
