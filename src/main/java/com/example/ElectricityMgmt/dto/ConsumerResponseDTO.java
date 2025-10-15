package com.example.ElectricityMgmt.dto;

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
    private boolean isConnected;
}
