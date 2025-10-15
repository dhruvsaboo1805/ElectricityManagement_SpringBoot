package com.example.ElectricityMgmt.services;

import com.example.ElectricityMgmt.dto.CustomerRequestDTO;
import com.example.ElectricityMgmt.dto.CustomerResponseDTO;

import java.util.List;

public interface ICustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) throws Exception;

    List<CustomerResponseDTO>  getAllCustomers();

    CustomerResponseDTO getCustomerById(Long id);
}
