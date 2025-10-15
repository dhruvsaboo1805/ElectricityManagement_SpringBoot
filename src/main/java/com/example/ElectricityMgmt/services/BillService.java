package com.example.ElectricityMgmt.services;

import com.example.ElectricityMgmt.dto.BillResponseDTO;
import com.example.ElectricityMgmt.entities.Bill;
import com.example.ElectricityMgmt.entities.Consumer;
import com.example.ElectricityMgmt.enums.PaymentStatus;
import com.example.ElectricityMgmt.mappers.BillMapper;
import com.example.ElectricityMgmt.repositries.IBillRepository;
import com.example.ElectricityMgmt.repositries.IConsumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BillService implements IBillService{
    private final IBillRepository billRepository;
    private final IConsumerRepository consumerRepository;

    @Override
    public List<BillResponseDTO> getBillByConsumerNumber(String consumerNumber) {
        Consumer consumer = consumerRepository.findByConsumerNumber(consumerNumber)
                .orElseThrow(() -> new RuntimeException("Consumer number not found"));
        return billRepository.findByConsumerId(consumer.getId()).stream()
                .map(BillMapper::maptoBillResponseDTOFromBill)
                .collect(Collectors.toList());
    }

    @Override
    public BillResponseDTO payBill(Long id) throws Exception {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new Exception("bill not found"));
        bill.setPaymentStatus(PaymentStatus.PAID);
        billRepository.save(bill);
        return BillMapper.maptoBillResponseDTOFromBill(bill);
    }

    @Override
    public List<BillResponseDTO> getAllBills() {
        return billRepository.findAll().stream()
                .map(BillMapper::maptoBillResponseDTOFromBill)
                .collect(Collectors.toList());
    }

    @Override
    public List<BillResponseDTO> getAllPaidBills() {
        return billRepository.findByPaymentStatus(PaymentStatus.PAID).stream()
                .map(BillMapper::maptoBillResponseDTOFromBill)
                .collect(Collectors.toList());
    }
}
