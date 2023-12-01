package com.who.messageservice.config.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.who.messageservice.dto.ChatNotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {

    @Value("${topic.name}")
    private String notifTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public String sendMessage(ChatNotificationDto chatNotificationDto) throws JsonProcessingException {
        String notifAsMessage = objectMapper.writeValueAsString(chatNotificationDto);
        kafkaTemplate.send(notifTopic, notifAsMessage);

        return "message sent";
    }
}
