package com.example.ElectricityMgmt.services;

import com.example.ElectricityMgmt.dto.CustomerRequestDTO;
import com.example.ElectricityMgmt.dto.CustomerResponseDTO;
import com.example.ElectricityMgmt.entities.Customer;
import com.example.ElectricityMgmt.entities.User;
import com.example.ElectricityMgmt.enums.RoleType;
import com.example.ElectricityMgmt.exceptions.UserNotFoundException;
import com.example.ElectricityMgmt.mappers.CustomerMapper;
import com.example.ElectricityMgmt.repositries.ICustomerRepository;
import com.example.ElectricityMgmt.repositries.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements ICustomerService {
    private final ICustomerRepository customerRepository;
    private final IUserRepository userRepository;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) throws Exception {
        if(userRepository.findByUsername(customerRequestDTO.getUsername()).isPresent()) {
            throw new UserNotFoundException("User already exists try to login");
        }
        User user = new User();
        user.setUsername(customerRequestDTO.getUsername());
        user.setPassword(customerRequestDTO.getPassword());
        user.setRole(RoleType.USER);

        Customer customer = CustomerMapper.mapCustomerRequestDTOToCustomer(customerRequestDTO);
        log.info("CustomerMapper.mapCustomerRequestDTOToCustomer -> customer={}", customer);

        customer.setUser(user);
        user.setCustomer(customer);
        userRepository.save(user);
        customerRepository.save(customer);

        log.info("customer created successfully");
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
        Customer customer = customerRepository.findById(id).orElse(null);
        log.info("CustomerMapper.getCustomerById -> customer={}", customer);

        if(customer != null){
            return CustomerMapper.mapCustomerToCustomerResponseDTO(customer);
        } else {
            return null;
        }
    }
}
