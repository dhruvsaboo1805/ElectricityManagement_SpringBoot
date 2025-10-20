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
//    private static String authCode="";
//    private static String username = "";
    private final IAdminService adminService;
    private final ICustomerService customerService;
    private final IBillService billService;
    private final IComplaintService complaintService;

//    @ModelAttribute
//    public void init(){
//        username = uname;
//        authCode = auth;
//    }

    @PostMapping("/register")
    public ResponseEntity<AdminSMEResponseDTO> createAdmin(@RequestBody AdminSMERequestDTO adminequestDTO ,  @RequestHeader("Username") String uname, @RequestHeader("Authorization") String auth) throws Exception {
        AdminSMEResponseDTO lDto = adminService.createAdmin(adminequestDTO);
        if(lDto != null) {
            return ResponseEntity.ok(lDto);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/allCustomers")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username,authCode)) {
            return ResponseEntity.status(401).body(null);
        }

        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping("/consumers")
    public ResponseEntity<ConsumerResponseDTO> addConsumer(@RequestBody ConsumerRequestDTO consumerRequestDTO , @RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username,authCode)) {
            System.out.println("inside admin add consumer");
            return ResponseEntity.status(401).body(null);
        }

        return ResponseEntity.ok(adminService.addConsumer(consumerRequestDTO));
    }

    @GetMapping("/allConsumers")
    public ResponseEntity<List<ConsumerResponseDTO>> getAllConsumers(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username,authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(adminService.getAllConsumers());
    }

    @PostMapping("/consumers/{consumerNumber}/toggle")
    public ResponseEntity<ConsumerResponseDTO> toggleConsumer(@PathVariable String consumerNumber,@RequestBody AdminUsernameDTO adminUsername , @RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username,authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(adminService.ToggleConnection(consumerNumber));
    }

    @PostMapping("/bills")
    public ResponseEntity<BillResponseDTO> addBill(@RequestBody BillRequestDTO billRequestDTO , @RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username,authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(adminService.addBill(billRequestDTO));
    }

    @GetMapping("/allBills")
    public ResponseEntity<List<BillResponseDTO>> getAllBills(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username , authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(billService.getAllBills());
    }

    @GetMapping("/allComplaints")
    public ResponseEntity<List<ComplaintResponseDTO>> getAllComplaints(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode){
        if(!auth.isValidAdminCode(username,authCode))
            return ResponseEntity.status(401).body(null);
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

}
