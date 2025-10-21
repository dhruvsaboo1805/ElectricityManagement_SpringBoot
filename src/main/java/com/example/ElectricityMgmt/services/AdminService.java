package com.example.ElectricityMgmt.services;

import com.example.ElectricityMgmt.dto.*;
import com.example.ElectricityMgmt.entities.Bill;
import com.example.ElectricityMgmt.entities.Consumer;
import com.example.ElectricityMgmt.entities.Customer;
import com.example.ElectricityMgmt.entities.User;
import com.example.ElectricityMgmt.enums.RoleType;
import com.example.ElectricityMgmt.exceptions.ConsumerNotFoundException;
import com.example.ElectricityMgmt.exceptions.CustomerNoFoundException;
import com.example.ElectricityMgmt.exceptions.UserNotFoundException;
import com.example.ElectricityMgmt.mappers.BillMapper;
import com.example.ElectricityMgmt.mappers.ConsumerMapper;
import com.example.ElectricityMgmt.repositries.IBillRepository;
import com.example.ElectricityMgmt.repositries.IConsumerRepository;
import com.example.ElectricityMgmt.repositries.ICustomerRepository;
import com.example.ElectricityMgmt.repositries.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService{

    private final IConsumerRepository consumerRepository;
    private final IBillRepository billRepository;
    private final ICustomerRepository customerRepository;
    private final IUserRepository userRepository;

    @Override
    public AdminSMEResponseDTO createAdmin(AdminSMERequestDTO adminSMERequestDTO) {
        User user = new User();
        if(userRepository.findByUsername(adminSMERequestDTO.getUsername()).isPresent()) {
            throw new UserNotFoundException("Admin already exists try to login");
        }
        user.setUsername(adminSMERequestDTO.getUsername());
        user.setPassword(adminSMERequestDTO.getPassword());
        user.setRole(RoleType.ADMIN);
        userRepository.save(user);
        AdminSMEResponseDTO adminSMEResponseDTO = new AdminSMEResponseDTO().builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(RoleType.ADMIN)
                .build();
        return adminSMEResponseDTO;
    }

    @Override
    public ConsumerResponseDTO addConsumer(ConsumerRequestDTO consumerRequestDTO) {
        Customer customer = customerRepository.findById(consumerRequestDTO.getCustomerId())
                .orElseThrow(() -> new CustomerNoFoundException("Customer Not Found"));

        if(consumerRepository.findByConsumerNumber(consumerRequestDTO.getConsumerNumber()).isPresent()){
            throw new CustomerNoFoundException("Consumer Already Exists");
        }

        Consumer consumer = new Consumer();
        consumer.setConsumerNumber(consumerRequestDTO.getConsumerNumber());

        customer.getConsumers().add(consumer);
        consumer.setCustomer(customer);
        consumerRepository.save(consumer);
        ConsumerResponseDTO consumerResponseDTO = new ConsumerResponseDTO().builder()
                .id(consumer.getId())
                .customerId(customer.getId())
                .consumerNumber(consumer.getConsumerNumber())
                .isConnected(consumer.isConnected())
                .build();
        return consumerResponseDTO;
    }

    @Override
    public BillResponseDTO addBill(BillRequestDTO billRequestDTO) {
        Consumer consumer = consumerRepository.findByConsumerNumber(billRequestDTO.getConsumerNumber())
                .orElseThrow(() -> new ConsumerNotFoundException("Consumer Not Found"));

        Bill bill = new Bill();
        bill.setBillNumber(billRequestDTO.getBillNumber());
        bill.setConsumer(consumer);
        bill.setMobileNumber(billRequestDTO.getMobileNumber());
        bill.setBillPeriod(billRequestDTO.getBillPeriod());
        bill.setBillDate(billRequestDTO.getBillDate());
        bill.setDueDate(billRequestDTO.getDueDate());
        bill.setDueAmount(billRequestDTO.getDueAmount());
        bill.setPayableAmount(billRequestDTO.getPayableAmount());
        bill.setPaymentStatus(billRequestDTO.getPaymentStatus());
        bill.setConnectionStatus(billRequestDTO.getConnectionStatus());
        bill.setConnectionType(billRequestDTO.getConnectionType());

        billRepository.save(bill);

        System.out.println("bill created successfully");
        return BillMapper.maptoBillResponseDTOFromBill(bill);
    }

    @Override
    public List<ConsumerResponseDTO> getAllConsumers() {
        return consumerRepository.findAll().stream()
                .map(ConsumerMapper::mapToConsumerResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ConsumerResponseDTO ToggleConnection(String consumerNumber) {
        Consumer consumer = consumerRepository.findByConsumerNumber(consumerNumber)
                .orElseThrow(() -> new ConsumerNotFoundException("Consumer Not Found"));
        consumer.setConnected(!consumer.isConnected());
        consumerRepository.save(consumer);
        return ConsumerMapper.mapToConsumerResponseDTO(consumer);
    }

    // todo will resolve the logic
    @Override
    public ConsumerResponseDTO updateConsumer(ConsumerRequestDTO consumerRequestDTO) {
        return null;

    }
}
