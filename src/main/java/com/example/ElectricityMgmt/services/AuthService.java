package com.example.ElectricityMgmt.services;

import com.example.ElectricityMgmt.dto.LoginRequestDTO;
import com.example.ElectricityMgmt.dto.LoginResponseDTO;
import com.example.ElectricityMgmt.entities.User;
import com.example.ElectricityMgmt.exceptions.UserNotFoundException;
import com.example.ElectricityMgmt.repositries.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final IUserRepository userRepository;

    public LoginResponseDTO LoginUser(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found please register yourself"));
        if(!user.getPassword().equals(loginRequestDTO.getPassword())){
            // change exception accordingly todo
            throw new UserNotFoundException("Credentials don't match");
        }
        return new LoginResponseDTO(user.getId() , user.getUsername() , user.getPassword() , user.getRole());

    }
}
