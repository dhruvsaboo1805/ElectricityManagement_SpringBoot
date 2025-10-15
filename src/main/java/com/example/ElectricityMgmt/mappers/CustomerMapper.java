package com.example.ElectricityMgmt.mappers;

import com.example.ElectricityMgmt.dto.CustomerRequestDTO;
import com.example.ElectricityMgmt.dto.CustomerResponseDTO;
import com.example.ElectricityMgmt.entities.Customer;
import com.example.ElectricityMgmt.entities.User;

public class CustomerMapper {

    public static CustomerResponseDTO mapCustomerToCustomerResponseDTO(Customer customer){
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO().builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .mobileNumber(customer.getMobileNumber())
                .userType(customer.getUserType())
                .electricalSectionType(customer.getElectricalSection())
                .created_at(customer.getCreated_at())
                .updated_at(customer.getUpdated_at())
                .build();
        return customerResponseDTO;
    }

    public static Customer mapCustomerResponseDTOToCustomer(CustomerResponseDTO customerResponseDTO) {
        Customer customer = new Customer().builder()
                .fullName(customerResponseDTO.getFullName())
                .email(customerResponseDTO.getEmail())
                .mobileNumber(customerResponseDTO.getMobileNumber())
                .address(customerResponseDTO.getAddress())
                .userType(customerResponseDTO.getUserType())
                .electricalSection(customerResponseDTO.getElectricalSectionType())
                .created_at(customerResponseDTO.getCreated_at())
                .updated_at(customerResponseDTO.getUpdated_at())
                .build();
        return customer;
    }

    public static Customer mapCustomerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = new Customer();
        customer.setFullName(customerRequestDTO.getFullName());
        customer.setAddress(customerRequestDTO.getAddress());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setMobileNumber(customerRequestDTO.getMobileNumber());
        customer.setUserType(customerRequestDTO.getUserType());
        customer.setElectricalSection(customerRequestDTO.getElectricalSectionType());
        return customer;
    }
}
