package com.example.oauth2_for_google.controller;

import com.example.oauth2_for_google.dto.LoginDto;
import com.example.oauth2_for_google.dto.RegisterDto;
import com.example.oauth2_for_google.entity.User;
import com.example.oauth2_for_google.security.jwt.JwtProvider;
import com.example.oauth2_for_google.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    final AuthenticationManager authenticationManager;
    final AuthService authService;
    final JwtProvider jwtProvider;

    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody LoginDto dto){
        String token = jwtProvider.generateToken(dto.getUsername());
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/me")
    public HttpEntity<?> getMe(User user) { //Parametr
        return ResponseEntity.ok().body("Mana" + user);
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody RegisterDto dto)  {
        return ResponseEntity.ok().body(authService.register(dto));
    }


}
