package com.example.ElectricityMgmt.dto;

import com.example.ElectricityMgmt.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminSMEResponseDTO {
    private Long id;
    private String username;
    private String password;
    private RoleType role;
}
