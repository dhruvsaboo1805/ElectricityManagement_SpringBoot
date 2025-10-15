package com.example.ElectricityMgmt.controllers;

import com.example.ElectricityMgmt.dto.*;
import com.example.ElectricityMgmt.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final IAdminService adminService;
    private final ICustomerService customerService;
    private final IBillService billService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping("/consumers")
    public ResponseEntity<ConsumerResponseDTO> addConsumer(@RequestBody ConsumerRequestDTO consumerRequestDTO){
        return ResponseEntity.ok(adminService.addConsumer(consumerRequestDTO));
    }

    @GetMapping("/consumers")
    public ResponseEntity<List<ConsumerResponseDTO>> getAllConsumers(){
        return ResponseEntity.ok(adminService.getAllConsumers());
    }

    @PostMapping("/consumers/{consumerNumber}/toggle")
    public ResponseEntity<ConsumerResponseDTO> toggleConsumer(@PathVariable String consumerNumber){
        return ResponseEntity.ok(adminService.ToggleConnection(consumerNumber));
    }

    @PostMapping("/bills")
    public ResponseEntity<BillResponseDTO> addBill(@RequestBody BillRequestDTO billRequestDTO){
        return ResponseEntity.ok(adminService.addBill(billRequestDTO));
    }

    @GetMapping("/bills")
    public ResponseEntity<List<BillResponseDTO>> getAllBills(){
        return ResponseEntity.ok(billService.getAllBills());
    }

    //TODO admin complaint route

}
