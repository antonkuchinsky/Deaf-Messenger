package ru.who.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.who.dto.AuthRequest;
import ru.who.exceptions.InvalidDataException;
import ru.who.models.User;
import ru.who.services.UserService;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody User user) throws JsonProcessingException {
        return userService.createNewUser(user);
    }

    @GetMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest){
        var authenticate=authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(authRequest.username(),authRequest.password()));
        if(authenticate.isAuthenticated()){
            return userService.generateToken(authRequest.username(),authRequest.userId());
        }
        else{
            throw new InvalidDataException("Invalid access", "Invalid access try again");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        userService.validateToken(token);
        return "Token is valid";
    }
}
