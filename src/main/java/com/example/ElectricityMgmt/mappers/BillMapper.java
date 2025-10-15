package com.example.ElectricityMgmt.mappers;

import com.example.ElectricityMgmt.dto.BillResponseDTO;
import com.example.ElectricityMgmt.entities.Bill;

public class BillMapper {
    public static BillResponseDTO maptoBillResponseDTOFromBill(Bill bill) {
        BillResponseDTO billResponseDTO = new BillResponseDTO().builder()
                .id(bill.getId())
                .billNumber(bill.getBillNumber())
                .mobileNumber(bill.getMobileNumber())
                .billPeriod(bill.getBillPeriod())
                .billDate(bill.getBillDate())
                .dueDate(bill.getDueDate())
                .dueAmount(bill.getDueAmount())
                .payableAmount(bill.getPayableAmount())
                .paymentStatus(bill.getPaymentStatus())
                .connectionStatus(bill.getConnectionStatus())
                .connectionType(bill.getConnectionType())
                .build();
        return billResponseDTO;
    }

    public Bill maptoBillFromBillResponseDTO(BillResponseDTO billResponseDTO) {
        Bill bill = new Bill().builder()
                .id(billResponseDTO.getId())
                .billNumber(billResponseDTO.getBillNumber())
                .mobileNumber(billResponseDTO.getMobileNumber())
                .billPeriod(billResponseDTO.getBillPeriod())
                .billDate(billResponseDTO.getBillDate())
                .dueDate(billResponseDTO.getDueDate())
                .dueAmount(billResponseDTO.getDueAmount())
                .payableAmount(billResponseDTO.getPayableAmount())
                .paymentStatus(billResponseDTO.getPaymentStatus())
                .connectionStatus(billResponseDTO.getConnectionStatus())
                .connectionType(billResponseDTO.getConnectionType())
                .build();
        return bill;
    }
}
