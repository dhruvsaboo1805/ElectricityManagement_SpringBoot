package com.example.ElectricityMgmt.services;

import com.example.ElectricityMgmt.dto.BillResponseDTO;

import java.util.List;

public interface IBillService {
    List<BillResponseDTO> getBillByConsumerNumber(String consumerNumber);
    BillResponseDTO payBill(Long id) throws Exception;
    List<BillResponseDTO> getAllBills();
    List<BillResponseDTO> getAllPaidBills(String consumerNumber);

}
