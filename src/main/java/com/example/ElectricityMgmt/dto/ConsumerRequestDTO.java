package com.example.ElectricityMgmt.dto;

import com.example.ElectricityMgmt.enums.ConnectionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ConsumerRequestDTO {
    private Long customerId;
    private String consumerNumber;
    private ConnectionType connectionType;
    private String mobileNumber;
}
