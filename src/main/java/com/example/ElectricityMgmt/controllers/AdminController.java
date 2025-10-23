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
    private final IAdminService adminService;
    private final ICustomerService customerService;
    private final IBillService billService;
    private final IComplaintService complaintService;


    @PostMapping("/register")
    public ResponseEntity<?> createAdmin(@RequestBody AdminSMERequestDTO adminequestDTO ,  @RequestHeader("Username") String uname, @RequestHeader("Authorization") String auth) throws Exception {
        AdminSMEResponseDTO lDto = adminService.createAdmin(adminequestDTO);
        if(lDto != null) {
            return ResponseEntity.ok(lDto);
        } else {
            return ResponseEntity.status(401).body("Unauthorized Access!!");
        }
    }

    @GetMapping("/allCustomers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username,authCode)) {
            return ResponseEntity.status(401).body("Unauthorized Access!!");
        }

        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping("/consumers")
    public ResponseEntity<?> addConsumer(@RequestBody ConsumerRequestDTO consumerRequestDTO , @RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username,authCode)) {
            return ResponseEntity.status(401).body("Unauthorized Access!!");
        }

        return ResponseEntity.ok(adminService.addConsumer(consumerRequestDTO));
    }

    @GetMapping("/allConsumers")
    public ResponseEntity<?> getAllConsumers(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");

        return ResponseEntity.ok(adminService.getAllConsumers());
    }

    @PostMapping("/consumers/{consumerNumber}/toggle")
    public ResponseEntity<?> toggleConsumer(@PathVariable String consumerNumber,@RequestBody AdminUsernameDTO adminUsername , @RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");

        return ResponseEntity.ok(adminService.ToggleConnection(consumerNumber));
    }

    @PostMapping("/bills")
    public ResponseEntity<?> addBill(@RequestBody BillRequestDTO billRequestDTO , @RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");

        return ResponseEntity.ok(adminService.addBill(billRequestDTO));
    }

    @GetMapping("/allBills")
    public ResponseEntity<?> getAllBills(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username , authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");

        return ResponseEntity.ok(billService.getAllBills());
    }

    @GetMapping("/allComplaints")
    public ResponseEntity<?> getAllComplaints(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");

        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

}
