package com.example.ElectricityMgmt.controllers;


import com.example.ElectricityMgmt.config.Auth;
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

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*" , methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final Auth auth;
    private static String authCode="";
    private static String username = "";
    private final AuthService authService;
    private final ICustomerService customerService;

    @ModelAttribute
    public void init(@RequestHeader("Username") String uname, @RequestHeader("Authorization") String auth){
        username = uname;
        authCode = auth;
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        LoginResponseDTO authDTO = authService.LoginUser(loginRequestDTO);
        HashMap<String,LoginResponseDTO> map = new HashMap<>();
        map.put("result",authDTO);
        if (authDTO != null) {
            return ResponseEntity.ok(map);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) throws Exception {
        CustomerResponseDTO cDto = customerService.createCustomer(customerRequestDTO);
        if(cDto != null) {
            return ResponseEntity.ok(cDto);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }
}
