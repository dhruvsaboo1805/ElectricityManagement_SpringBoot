package com.example.ElectricityMgmt.dto;

import com.example.ElectricityMgmt.enums.ElectricalSectionType;
import com.example.ElectricityMgmt.enums.RoleType;
import com.example.ElectricityMgmt.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerRequestDTO {
    private String fullName;
    private String email;
    private String address;
    private String mobileNumber;
    private UserType userType;
    private ElectricalSectionType electricalSectionType;
    private String username;
    private String password;
}
