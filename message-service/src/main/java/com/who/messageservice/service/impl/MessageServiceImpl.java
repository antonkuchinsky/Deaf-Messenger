package com.who.messageservice.service.impl;

import com.who.messageservice.dto.MessageDto;
import com.who.messageservice.entity.Chat;
import com.who.messageservice.entity.Message;
import com.who.messageservice.exception.InvalidDataException;
import com.who.messageservice.repository.ChatRepository;
import com.who.messageservice.repository.MessageRepository;
import com.who.messageservice.service.MessageService;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public MessageServiceImpl(MessageRepository messageRepository, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
    }


    @Override
    public Message sendMessage(Message message) {
        UUID chatId = message.getChatId();
        Chat chat = chatRepository.findById(chatId).orElse(null);

        if (chat == null) {
            chat = Chat.builder()
                    .nameChat("Chat " + chatId)
                    .community(message.getRecipients())
                    .build();
            chatRepository.save(chat);
        }
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
