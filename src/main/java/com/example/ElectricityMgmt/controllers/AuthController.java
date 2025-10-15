package com.example.ElectricityMgmt.controllers;


import com.example.ElectricityMgmt.dto.CustomerRequestDTO;
import com.example.ElectricityMgmt.dto.CustomerResponseDTO;
import com.example.ElectricityMgmt.dto.LoginRequestDTO;
import com.example.ElectricityMgmt.dto.LoginResponseDTO;
import com.example.ElectricityMgmt.services.AuthService;
import com.example.ElectricityMgmt.services.CustomerService;
import com.example.ElectricityMgmt.services.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ICustomerService customerService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> LoginUser(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        LoginResponseDTO authDTO = authService.LoginUser(loginRequestDTO);
        if (authDTO != null) {
            return ResponseEntity.ok(authDTO);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("register")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) throws Exception {
        CustomerResponseDTO cDto = customerService.createCustomer(customerRequestDTO);
        if(cDto != null) {
            return ResponseEntity.ok(cDto);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }
}
