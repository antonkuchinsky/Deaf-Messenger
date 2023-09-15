package com.who.messageservice.service;

import com.who.messageservice.dto.ChatInitializationDTO;
import com.who.messageservice.entity.Chat;


public interface ChatService {
    Chat getExistingChannel(ChatInitializationDTO chatInitializationDTO);
    Chat newChatSession(ChatInitializationDTO chatInitializationDTO);
    Chat establishChatSession(ChatInitializationDTO chatInitializationDTO);
}
