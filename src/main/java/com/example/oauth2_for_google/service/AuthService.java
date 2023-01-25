package com.example.oauth2_for_google.service;

import com.example.oauth2_for_google.dto.ApiResponse;
import com.example.oauth2_for_google.dto.RegisterDto;
import com.example.oauth2_for_google.entity.User;
import com.example.oauth2_for_google.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    final PasswordEncoder passwordEncoder;
    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
    public Object register(RegisterDto dto) {
        boolean byUsername = userRepository.existsByUsername(dto.getUsername());
        if (byUsername) {
            return new ApiResponse("This username is already exist", false);
        }
        boolean byEmail = userRepository.existsByEmail(dto.getEmail());
        if (byEmail) {
            return new ApiResponse("This email is already exist", false);
        }
        User user = new User();
        if (dto.getEmail().contains("@")) {
            user.setEmail(dto.getEmail());
        }
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        userRepository.save(user);
        return new ApiResponse("Success", true);
    }

}
