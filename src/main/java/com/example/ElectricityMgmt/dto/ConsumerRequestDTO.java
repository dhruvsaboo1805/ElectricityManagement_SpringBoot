package com.example.ElectricityMgmt.dto;

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
}
