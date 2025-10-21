package com.example.ElectricityMgmt.dto;

import com.example.ElectricityMgmt.enums.ConnectionStatus;
import com.example.ElectricityMgmt.enums.ConnectionType;
import com.example.ElectricityMgmt.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillRequestDTO {
    private String billNumber;

    private String consumerNumber;

    private String mobileNumber; // Mobile number associated with the bill

    private String billPeriod; // Bill period (e.g., "Jan 2025")

    private LocalDate billDate; // Date when the bill was generated

    private LocalDate dueDate; // Date when the bill is due

    private Double dueAmount; // The due amount to be paid

    private Double payableAmount; // The total amount to be paid

    private PaymentStatus paymentStatus; // Payment status (PAID/UNPAID)

    private ConnectionType connectionType; // Type of connection (DOMESTIC/COMMERCIAL)

    private ConnectionStatus connectionStatus; // Connection status (CONNECTED/DISCONNECTED)

}
