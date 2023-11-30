package ru.spellsofenglish.mapper;

import org.springframework.stereotype.Service;
import ru.spellsofenglish.dto.UserDto;
import ru.spellsofenglish.models.User;

import java.util.function.Function;

@Service
public class UserMapperDto implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername()
        );
    }
}
