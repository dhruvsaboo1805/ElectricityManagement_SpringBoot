package com.example.ElectricityMgmt.services;

import com.example.ElectricityMgmt.dto.*;

import java.util.List;

public interface IAdminService {
    AdminResponseDTO createAdmin(AdminRequestDTO adminRequestDTO);
    ConsumerResponseDTO addConsumer(ConsumerRequestDTO consumerRequestDTO);
    BillResponseDTO addBill(BillRequestDTO billRequestDTO);
    List<ConsumerResponseDTO> getAllConsumers();
    ConsumerResponseDTO ToggleConnection(String consumerNumber);
    // the last api i will think its TODO
    ConsumerResponseDTO updateConsumer(ConsumerRequestDTO consumerRequestDTO);
}
