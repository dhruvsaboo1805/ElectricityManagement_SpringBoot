package com.example.ElectricityMgmt.services;

import com.example.ElectricityMgmt.dto.BillRequestDTO;
import com.example.ElectricityMgmt.dto.BillResponseDTO;
import com.example.ElectricityMgmt.dto.ConsumerRequestDTO;
import com.example.ElectricityMgmt.dto.ConsumerResponseDTO;

import java.util.List;

public interface IAdminService {
    ConsumerResponseDTO addConsumer(ConsumerRequestDTO consumerRequestDTO);
    BillResponseDTO addBill(BillRequestDTO billRequestDTO);
    List<ConsumerResponseDTO> getAllConsumers();
    ConsumerResponseDTO ToggleConnection(String consumerNumber);
    // the last api i will think its TODO
    ConsumerResponseDTO updateConsumer(ConsumerRequestDTO consumerRequestDTO);
}
