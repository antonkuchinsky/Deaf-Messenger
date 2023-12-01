package ru.who.config.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.who.dto.UserDto;

@Component
@RequiredArgsConstructor
public class Producer {

    @Value("${topic.name}")
    private String orderTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public String sendMessage(UserDto userDto) throws JsonProcessingException {
        String userAsMessage = objectMapper.writeValueAsString(userDto);
        kafkaTemplate.send(orderTopic, userAsMessage);

        return "message sent";
    }
}
