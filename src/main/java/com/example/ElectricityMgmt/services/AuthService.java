package com.example.ElectricityMgmt.services;

import com.example.ElectricityMgmt.config.Auth;
import com.example.ElectricityMgmt.dto.LoginRequestDTO;
import com.example.ElectricityMgmt.dto.LoginResponseDTO;
import com.example.ElectricityMgmt.entities.User;
import com.example.ElectricityMgmt.exceptions.UserNotFoundException;
import com.example.ElectricityMgmt.repositries.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final IUserRepository userRepository;
    private final Auth auth;

    public LoginResponseDTO LoginUser(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found please register yourself"));
        if(!user.getPassword().equals(loginRequestDTO.getPassword())){
            throw new UserNotFoundException("Credentials don't match");
        }

        String token;
        switch (user.getRole()) {
            case CUSTOMER:
                token = auth.generateUserAuthCode(user.getUsername());
                break;
            case ADMIN:
                token = auth.generateAdminAuthCode(user.getUsername());
                break;
            case SME:
                token = auth.generateSmeAuthCode(user.getUsername());
                break;
            default:
                throw new IllegalStateException("User has an undefined role: " + user.getRole());
        }

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setId(user.getId());
        loginResponseDTO.setUsername(user.getUsername());
        loginResponseDTO.setPassword(user.getPassword());
        loginResponseDTO.setRole(user.getRole());
        loginResponseDTO.setAuthToken(token);
        return loginResponseDTO;
    }
}
