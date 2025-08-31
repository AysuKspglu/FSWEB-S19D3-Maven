package com.workintech.s19d2.controller;

import com.workintech.s19d2.dto.RegisterResponse;
import com.workintech.s19d2.dto.RegistrationMember;
import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workintech/auth")
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegistrationMember dto) {
        Member saved = authService.register(dto.email(), dto.password());
        return new RegisterResponse(saved.getEmail(), "Member registered");
    }
}
