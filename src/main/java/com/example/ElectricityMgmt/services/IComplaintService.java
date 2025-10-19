package com.example.ElectricityMgmt.services;

import com.example.ElectricityMgmt.dto.ComplaintRequestDTO;
import com.example.ElectricityMgmt.dto.ComplaintResponseDTO;
import com.example.ElectricityMgmt.enums.ComplaintStatus;
import com.example.ElectricityMgmt.enums.ComplaintType;

import java.util.List;

public interface IComplaintService {
    ComplaintResponseDTO registerComplaint(ComplaintRequestDTO complaintResponseDTO);

     List<ComplaintResponseDTO> getComplaintsByConsumerNumber(String consumerNumber);

    List<ComplaintResponseDTO> getComplaintsByComplaintNumber(String complaintNumber);

    List<ComplaintResponseDTO> getComplaintByType(ComplaintType complaintType);

    List<ComplaintResponseDTO> getComplaintByStatus(ComplaintStatus complaintStatus);

    ComplaintResponseDTO changeStatus(ComplaintStatus complaintStatus,Long id);

    List<ComplaintResponseDTO> getAllComplaints();
}
