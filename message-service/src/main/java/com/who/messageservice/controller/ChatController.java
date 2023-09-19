package com.who.messageservice.controller;

import com.who.messageservice.dto.ChatDto;
import com.who.messageservice.dto.ChatInitializationDTO;
import com.who.messageservice.dto.MessageDto;
import com.who.messageservice.entity.Message;
import com.who.messageservice.http.JSONResponseHelper;
import com.who.messageservice.service.ChatService;
import com.who.messageservice.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
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
    @RequestMapping(value="/api/v1/private-chat/chat", method=RequestMethod.PUT, produces="application/json", consumes="application/json")
    public ResponseEntity<String> establishChatChannel(@RequestBody ChatInitializationDTO chatInitialization){
        var chat = chatService.establishChatSession(chatInitialization);
        ChatDto chatDto=new ChatDto(chat.getId(),chat.getUserIdOne(),chat.getUserIdTwo());
        return JSONResponseHelper.createResponse(chatDto, HttpStatus.OK);
    }
    @RequestMapping(value="/api/v1/private-chat/chat/{chatId}", method=RequestMethod.GET, produces="application/json")
    public ResponseEntity<String> getExistingChatMessages(@PathVariable("chatId") UUID chatId) {
        List<Message> messages=messageService.getExistingChatMessages(chatId);
        return JSONResponseHelper.createResponse(messages, HttpStatus.OK);
    }


}
