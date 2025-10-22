package com.example.ElectricityMgmt.services;

import com.example.ElectricityMgmt.dto.ConsumerResponseDTO;
import com.example.ElectricityMgmt.dto.CustomerRequestDTO;
import com.example.ElectricityMgmt.dto.CustomerResponseDTO;
import com.example.ElectricityMgmt.entities.Consumer;
import com.example.ElectricityMgmt.entities.Customer;
import com.example.ElectricityMgmt.entities.User;
import com.example.ElectricityMgmt.enums.RoleType;
import com.example.ElectricityMgmt.exceptions.UserNotFoundException;
import com.example.ElectricityMgmt.mappers.ConsumerMapper;
import com.example.ElectricityMgmt.mappers.CustomerMapper;
import com.example.ElectricityMgmt.repositries.IConsumerRepository;
import com.example.ElectricityMgmt.repositries.ICustomerRepository;
import com.example.ElectricityMgmt.repositries.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final ICustomerRepository customerRepository;
    private final IUserRepository userRepository;
    private final IConsumerRepository consumerRepository;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) throws Exception {
        if(userRepository.findByUsername(customerRequestDTO.getUsername()).isPresent()) {
            throw new UserNotFoundException("User already exists try to login");
        }
        User user = new User();
        user.setUsername(customerRequestDTO.getUsername());
        user.setPassword(customerRequestDTO.getPassword());
        user.setRole(RoleType.CUSTOMER);

        Customer customer = CustomerMapper.mapCustomerRequestDTOToCustomer(customerRequestDTO);

        customer.setUser(user);
        user.setCustomer(customer);
        userRepository.save(user);
        customerRepository.save(customer);


        System.out.println("customer created successfully");
        return CustomerMapper.mapCustomerToCustomerResponseDTO(customer);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::mapCustomerToCustomerResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        if(customer != null){
            return CustomerMapper.mapCustomerToCustomerResponseDTO(customer);
        } else {
            return null;
        }
    }


    // todo some problem with logic
    @Override
    public List<ConsumerResponseDTO> getAllConsumers() {
        List<Consumer> consumers = consumerRepository.findAll();

        List<ConsumerResponseDTO> response = consumers.stream()
                .map(ConsumerMapper::mapToConsumerResponseDTO) // Using the mapper
                .collect(Collectors.toList());

        return response;
    }
}
