package com.who.messageservice.client;

import com.who.messageservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name="user-service")
public interface UserClient {
    @GetMapping("/api/v1/players/{id}")
    Optional<UserDto> getPlayerById(@PathVariable("id") UUID id);
}
