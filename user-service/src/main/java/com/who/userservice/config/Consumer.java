package com.who.userservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.who.userservice.dto.UserRequestDto;
import com.who.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {

    private static final String regTopic = "${topic.name}";

    private final ObjectMapper objectMapper;
    private final UserService userService;

    @KafkaListener(topics = regTopic)
    public void consumeMessage(String message) throws JsonProcessingException {

        var userDto = objectMapper.readValue(message, UserRequestDto.class);
        userService.createUser(userDto);
    }

}