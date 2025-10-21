package com.example.ElectricityMgmt.services;

import com.example.ElectricityMgmt.dto.AdminSMERequestDTO;
import com.example.ElectricityMgmt.dto.AdminSMEResponseDTO;
import com.example.ElectricityMgmt.entities.User;
import com.example.ElectricityMgmt.enums.RoleType;
import com.example.ElectricityMgmt.exceptions.UserNotFoundException;
import com.example.ElectricityMgmt.repositries.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SMEService {
    private final IUserRepository userRepository;

    public AdminSMEResponseDTO createSME(AdminSMERequestDTO adminSMERequestDTO) throws Exception{
        User user = new User();
        if(userRepository.findByUsername(adminSMERequestDTO.getUsername()).isPresent()) {
            throw new UserNotFoundException("SME already exists try to login");
        }
        user.setUsername(adminSMERequestDTO.getUsername());
        user.setPassword(adminSMERequestDTO.getPassword());
        user.setRole(RoleType.SME);
        userRepository.save(user);
        AdminSMEResponseDTO adminSMEResponseDTO = new AdminSMEResponseDTO().builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(RoleType.SME)
                .build();
        return adminSMEResponseDTO;
    }
}
