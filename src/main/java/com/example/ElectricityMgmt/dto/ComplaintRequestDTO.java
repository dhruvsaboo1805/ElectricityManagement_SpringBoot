package com.example.ElectricityMgmt.dto;

import com.example.ElectricityMgmt.enums.ComplaintCategory;
import com.example.ElectricityMgmt.enums.ComplaintStatus;
import com.example.ElectricityMgmt.enums.ComplaintType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintRequestDTO {
    private String consumerNumber;
    private ComplaintType complaintType;
    private ComplaintCategory complaintCategory;
    private String landMark;
    private String description;
    private String address;
    private String mobileNumber;
    private ComplaintStatus complaintStatus;
}
