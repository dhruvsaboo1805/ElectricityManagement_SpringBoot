package com.example.ElectricityMgmt.mappers;

import com.example.ElectricityMgmt.dto.ComplaintResponseDTO;
import com.example.ElectricityMgmt.entities.Complaint;

public class ComplaintMapper {
    public static ComplaintResponseDTO maptoComplaintResponseDTOtoComplaint(Complaint complaint){
        ComplaintResponseDTO complaintResponseDTO=new ComplaintResponseDTO().builder()
                .id(complaint.getId())
                .complaintCategory(complaint.getComplaintCategory())
                .complaintType(complaint.getComplaintType())
                .complaintNumber(complaint.getComplaintNumber())
                .address(complaint.getAddress())
                .landMark(complaint.getLandMark())
                .description(complaint.getDescription())
                .mobileNumber(complaint.getMobileNumber())
                .consumerNumber(complaint.getConsumer().getConsumerNumber())
                .complaintStatus(complaint.getComplaintStatus())
                .build();
        return complaintResponseDTO;
    }

public static Complaint mapComplaintFromComplaintResponseDTO(ComplaintResponseDTO complaintResponseDTO){
Complaint complaint=new Complaint().builder()
        .id(complaintResponseDTO.getId())
        .complaintCategory(complaintResponseDTO.getComplaintCategory())
        .complaintType(complaintResponseDTO.getComplaintType())
        .complaintNumber(complaintResponseDTO.getComplaintNumber())
        .complaintStatus(complaintResponseDTO.getComplaintStatus())
        .landMark(complaintResponseDTO.getLandMark())
        .address(complaintResponseDTO.getAddress())
        .description(complaintResponseDTO.getDescription())
        .mobileNumber(complaintResponseDTO.getMobileNumber())
        .complaintStatus(complaintResponseDTO.getComplaintStatus())
        .build();
        return complaint;
    }
}
