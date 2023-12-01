package ru.who.mapper;

import org.springframework.stereotype.Service;
import ru.who.dto.UserDto;
import ru.who.models.User;

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
