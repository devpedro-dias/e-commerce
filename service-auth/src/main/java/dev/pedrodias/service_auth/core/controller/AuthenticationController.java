package dev.pedrodias.service_auth.core.controller;

import dev.pedrodias.service_auth.core.dto.SignUpDto;
import dev.pedrodias.service_auth.core.service.SignUpService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    @Autowired
    private SignUpService signUpService;


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@Validated @RequestBody SignUpDto signUpDto) {
        signUpService.createUser(signUpDto);
    }
}
