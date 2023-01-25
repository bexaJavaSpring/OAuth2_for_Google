package com.example.oauth2_for_google.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {
    private String email;
    private String password;
    private String username;

}
