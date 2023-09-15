package com.who.messageservice.mapper;

import com.who.messageservice.dto.MessageDto;
import com.who.messageservice.entity.Message;
import com.who.messageservice.entity.MessageStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.function.Function;
@Service
public class MessageMapperDto implements Function<MessageDto, Message> {
    @Override
    public Message apply(@NotNull MessageDto messageDto) {
        return Message.builder()
                .chatId(messageDto.chatId())
                .senderId(messageDto.senderId())
                .recipientId(messageDto.recipientId())
                .textMessage(messageDto.text())
                .dateTimeSendingMessage(ZonedDateTime.now())
                .messageStatus(MessageStatus.RECEIVED)
                .build();
    }
}

