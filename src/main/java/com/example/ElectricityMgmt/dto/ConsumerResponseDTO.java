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
public class ConsumerResponseDTO {
    private Long id;
    private Long customerId;
    private String consumerNumber;
    private ConnectionType connectionType;
    private String mobileNumber;
    private boolean isConnected;
}
