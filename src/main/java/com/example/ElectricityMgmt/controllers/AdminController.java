package com.example.ElectricityMgmt.controllers;

import com.example.ElectricityMgmt.config.Auth;
import com.example.ElectricityMgmt.dto.*;
import com.example.ElectricityMgmt.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*" , methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final Auth auth;
    private static String authCode="";
    private static String username = "";
    private final IAdminService adminService;
    private final ICustomerService customerService;
    private final IBillService billService;
    private final IComplaintService complaintService;

    @ModelAttribute
    public void init(@RequestHeader("Username") String uname, @RequestHeader("Authorization") String auth){
        username = uname;
        authCode = auth;
    }

    @PostMapping("/register")
    public ResponseEntity<AdminSMEResponseDTO> createAdmin(@RequestBody AdminSMERequestDTO adminequestDTO) throws Exception {
        AdminSMEResponseDTO lDto = adminService.createAdmin(adminequestDTO);
        if(lDto != null) {
            return ResponseEntity.ok(lDto);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/allCustomers")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers(){
        if(!auth.isValidAdminCode(username,authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping("/consumers")
    public ResponseEntity<ConsumerResponseDTO> addConsumer(@RequestBody ConsumerRequestDTO consumerRequestDTO){
        if(!auth.isValidAdminCode(consumerRequestDTO.getUsername(),authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(adminService.addConsumer(consumerRequestDTO));
    }

    @GetMapping("/allConsumers")
    public ResponseEntity<List<ConsumerResponseDTO>> getAllConsumers(){
        if(!auth.isValidAdminCode(username,authCode))
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

    @GetMapping("/allBills")
    public ResponseEntity<List<BillResponseDTO>> getAllBills(){
        if(!auth.isValidAdminCode(username , authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(billService.getAllBills());
    }

    @GetMapping("/allComplaints")
    public ResponseEntity<List<ComplaintResponseDTO>> getAllComplaints(){
        if(!auth.isValidAdminCode(username,authCode))
            return ResponseEntity.status(401).body(null);
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

}
