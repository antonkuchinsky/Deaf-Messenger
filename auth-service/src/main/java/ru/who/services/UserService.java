package ru.who.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.who.config.kafka.Producer;
import ru.who.exceptions.UserExistsException;
import ru.who.mapper.UserMapperDto;
import ru.who.models.User;
import ru.who.repositories.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final Producer producer;
    private final UserMapperDto userMapperDto;


    public String createNewUser(User user) throws JsonProcessingException {
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new UserExistsException("The user with the same name already exists","The user already exists");
        }
        else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            var userId=userRepository.findByUsername(user.getUsername()).get().getId();
            return producer.sendMessage(userMapperDto.apply(user));
        }
    }

    public String generateToken(String username, UUID userId){
        return jwtService.generateToken(username, userId);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }
}
