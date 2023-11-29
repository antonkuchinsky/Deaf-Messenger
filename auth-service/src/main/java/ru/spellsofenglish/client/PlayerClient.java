package ru.spellsofenglish.client;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import ru.spellsofenglish.dto.UserDto;

@FeignClient(name = "player-service")
public interface PlayerClient {

    @PostMapping("api/v1/players/create")
    void createPlayer(@Valid UserDto playerDto);
}