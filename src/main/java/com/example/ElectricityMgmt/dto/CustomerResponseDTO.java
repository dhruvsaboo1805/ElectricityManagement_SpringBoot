package com.example.ElectricityMgmt.dto;

import com.example.ElectricityMgmt.entities.Customer;
import com.example.ElectricityMgmt.enums.ElectricalSectionType;
import com.example.ElectricityMgmt.enums.RoleType;
import com.example.ElectricityMgmt.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerResponseDTO {
    private Long id;
    private String fullName;
    private String email;
    private String mobileNumber;
    private String address;
    private UserType userType;
    private ElectricalSectionType electricalSectionType;
    private Instant created_at;
    private Instant updated_at;

}
