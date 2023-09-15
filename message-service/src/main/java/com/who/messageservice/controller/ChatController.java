package com.who.messageservice.controller;

import com.who.messageservice.dto.ChatDto;
import com.who.messageservice.dto.ChatInitializationDTO;
import com.who.messageservice.dto.MessageDto;
import com.who.messageservice.entity.Message;
import com.who.messageservice.service.ChatService;
import com.who.messageservice.service.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(value="/api/v1/message")
@RestController
public class ChatController {
    private final MessageService messageService;
    private final ChatService chatService;

    public ChatController(MessageService messageService, ChatService chatService) {
        this.messageService = messageService;
        this.chatService = chatService;
    }

    @MessageMapping("/private.chat.{chatId}")
    @SendTo("/topic/private.chat.{chatId}")
    public MessageDto sendMessage(@DestinationVariable UUID chatId,
                               MessageDto message){
        messageService.sendMessage(message);
        return message;
    }
    @PutMapping("/private-chat")
    public ChatDto establishChatChannel(@RequestBody ChatInitializationDTO chatInitialization){
        var chat = chatService.establishChatSession(chatInitialization);
        ChatDto chatDto=new ChatDto(chat.getId(),chat.getUserIdOne(),chat.getUserIdTwo());
        return chatDto;
    }
    @GetMapping("private-chat/{chatId}")
    public List<Message> getExistingChatMessages(@PathVariable("chatId") UUID chatId) {
        return messageService.getExistingChatMessages(chatId);
    }


}
