package com.example.ElectricityMgmt.controllers;

import com.example.ElectricityMgmt.config.Auth;
import com.example.ElectricityMgmt.dto.*;
import com.example.ElectricityMgmt.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*" , methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private final Auth auth;
    private static String authCode="";
    private final IAdminService adminService;
    private final ICustomerService customerService;
    private final IBillService billService;

    @ModelAttribute
    public void init(@RequestHeader("Authorization") String auth){
        authCode = auth;
    }

    @PostMapping("/register")
    public ResponseEntity<AdminResponseDTO> createAdmin(@RequestBody AdminRequestDTO adminequestDTO) throws Exception {
        AdminResponseDTO lDto = adminService.createAdmin(adminequestDTO);
        if(lDto != null) {
            return ResponseEntity.ok(lDto);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers(@RequestBody AdminUsernameDTO adminUsername){
        if(!auth.isValidAdminCode(adminUsername.getUsername(),authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping("/consumers")
    public ResponseEntity<ConsumerResponseDTO> addConsumer(@RequestBody ConsumerRequestDTO consumerRequestDTO){
        if(!auth.isValidAdminCode(consumerRequestDTO.getUsername(),authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(adminService.addConsumer(consumerRequestDTO));
    }

    @PostMapping("/getAllConsumers")
    public ResponseEntity<List<ConsumerResponseDTO>> getAllConsumers(@RequestBody AdminUsernameDTO adminUsername){
        if(!auth.isValidAdminCode(adminUsername.getUsername(),authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(adminService.getAllConsumers());
    }

    @PostMapping("/consumers/{consumerNumber}/toggle")
    public ResponseEntity<ConsumerResponseDTO> toggleConsumer(@PathVariable String consumerNumber,@RequestBody AdminUsernameDTO adminUsername){
        if(!auth.isValidAdminCode(adminUsername.getUsername(),authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(adminService.ToggleConnection(consumerNumber));
    }

    @PostMapping("/bills")
    public ResponseEntity<BillResponseDTO> addBill(@RequestBody BillRequestDTO billRequestDTO){
        if(!auth.isValidAdminCode(billRequestDTO.getUsername(),authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(adminService.addBill(billRequestDTO));
    }

    @PostMapping("/getAllBills")
    public ResponseEntity<List<BillResponseDTO>> getAllBills(@RequestBody AdminUsernameDTO adminUsername){
        if(!auth.isValidAdminCode(adminUsername.getUsername(),authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(billService.getAllBills());
    }

    //TODO admin complaint route

}
