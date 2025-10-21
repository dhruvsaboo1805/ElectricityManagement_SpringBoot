package com.example.ElectricityMgmt.services;

import com.example.ElectricityMgmt.dto.ComplaintRequestDTO;
import com.example.ElectricityMgmt.dto.ComplaintResponseDTO;
import com.example.ElectricityMgmt.entities.Complaint;
import com.example.ElectricityMgmt.entities.Consumer;
import com.example.ElectricityMgmt.enums.ComplaintStatus;
import com.example.ElectricityMgmt.enums.ComplaintType;
import com.example.ElectricityMgmt.exceptions.ComplaintNotFoundException;
import com.example.ElectricityMgmt.exceptions.ConsumerNotFoundException;
import com.example.ElectricityMgmt.mappers.ComplaintMapper;
import com.example.ElectricityMgmt.repositries.IComplaintRepository;
import com.example.ElectricityMgmt.repositries.IConsumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ComplaintService implements IComplaintService {

    private final IConsumerRepository consumerRepository;
    private final IComplaintRepository complaintRepository;
    @Override
    public ComplaintResponseDTO registerComplaint(ComplaintRequestDTO complaintRequestDTO) {
        Consumer consumer=
                consumerRepository.findByConsumerNumber(complaintRequestDTO.getConsumerNumber()).orElseThrow(()-> new ConsumerNotFoundException("Consumer Not found"));
        Complaint complaint=new Complaint();
        complaint.setComplaintNumber(complaintRequestDTO.getComplaintNumber());
        complaint.setComplaintCategory(complaintRequestDTO.getComplaintCategory());
        complaint.setComplaintType(complaintRequestDTO.getComplaintType());
        complaint.setAddress(complaintRequestDTO.getAddress());
        complaint.setLandMark(complaintRequestDTO.getLandMark());
        complaint.setMobileNumber(complaintRequestDTO.getMobileNumber());
        complaint.setDescription(complaintRequestDTO.getDescription());
        complaint.setComplaintStatus(complaintRequestDTO.getComplaintStatus());
        complaint.setConsumer(consumer);

    complaintRepository.save(complaint);

    System.out.println("Complaint Registered SuccessFully");

    return ComplaintMapper.maptoComplaintResponseDTOtoComplaint(complaint);
    }

    @Override
    public List<ComplaintResponseDTO> getComplaintsByConsumerNumber(String consumerNumber) {
        Consumer consumer = consumerRepository.findByConsumerNumber(consumerNumber)
                .orElseThrow(()-> new ConsumerNotFoundException("Consumer Not found"));

        List<ComplaintResponseDTO> response = new ArrayList<>();
        for(Complaint complaint : consumer.getComplaints()){
            response.add(ComplaintMapper.maptoComplaintResponseDTOtoComplaint(complaint));
        }
        return response;
    }

    @Override
    public List<ComplaintResponseDTO> getComplaintsByComplaintNumber(String complaintNumber) {
        return complaintRepository.findByComplaintNumber(complaintNumber).stream().map(ComplaintMapper::maptoComplaintResponseDTOtoComplaint).toList();
    }

    @Override
    public List<ComplaintResponseDTO> getComplaintByType(ComplaintType complaintType) {
        return complaintRepository.findByComplaintType(complaintType).stream().map(ComplaintMapper::maptoComplaintResponseDTOtoComplaint).toList();
    }

    @Override
    public List<ComplaintResponseDTO> getComplaintByStatus(ComplaintStatus complaintStatus) {
        return complaintRepository.findByComplaintStatus(complaintStatus).stream().map(ComplaintMapper::maptoComplaintResponseDTOtoComplaint).toList();
    }

    @Override
    public ComplaintResponseDTO changeStatus(ComplaintStatus complaintStatus, Long id) {
        Complaint complaint=complaintRepository.findById(id).orElseThrow(()-> new ComplaintNotFoundException(
                "Complaint " +
                "with not found with "+ id));
        complaint.setComplaintStatus(complaintStatus);
        complaintRepository.save(complaint);

        return ComplaintMapper.maptoComplaintResponseDTOtoComplaint(complaint);
    }

    @Override
    public List<ComplaintResponseDTO> getAllComplaints() {
        return complaintRepository.findAll().stream()
                .map(ComplaintMapper::maptoComplaintResponseDTOtoComplaint)
                .collect(Collectors.toList());
    }
}
