package com.example.ElectricityMgmt.controllers;

import com.example.ElectricityMgmt.config.Auth;
import com.example.ElectricityMgmt.dto.BillResponseDTO;
import com.example.ElectricityMgmt.dto.ComplaintRequestDTO;
import com.example.ElectricityMgmt.dto.ComplaintResponseDTO;
import com.example.ElectricityMgmt.enums.ComplaintStatus;
import com.example.ElectricityMgmt.services.IBillService;
import com.example.ElectricityMgmt.services.IComplaintService;
import com.example.ElectricityMgmt.services.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*" , methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final Auth auth;
    private final ICustomerService customerService;
    private final IBillService billService;
    private final IComplaintService complaintService;


    @GetMapping("/{consumerNumber}/bills")
    public ResponseEntity<?> getBillByConsumerNumber(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode , @PathVariable("consumerNumber") String consumerNumber){
        if(!auth.isValidUserCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");

        return ResponseEntity.ok(billService.getBillByConsumerNumber(consumerNumber));
    }

    @PostMapping("/bills/{id}/pay")
    public ResponseEntity<?> payBill(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode , @PathVariable Long id) throws Exception {
        if(!auth.isValidUserCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");

        return ResponseEntity.ok(billService.payBill(id));
    }


    @GetMapping("/bills/{consumerNumber}/paidBills")
    public ResponseEntity<?> getAllPaidBills(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode , @PathVariable String consumerNumber) throws Exception {
        if(!auth.isValidUserCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");

        return  ResponseEntity.ok(billService.getAllPaidBills(consumerNumber));
    }

    @PostMapping("/registerComplaint")
    public ResponseEntity<?> registerComplaint(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode , @RequestBody ComplaintRequestDTO complaintRequestDTO) throws Exception {
        if(!auth.isValidUserCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");
        return ResponseEntity.ok(complaintService.registerComplaint(complaintRequestDTO));
    }

    @PostMapping("/complaints/{complaintStatus}/byStatus")
    public ResponseEntity<?> getComplaintByStatus(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode , @PathVariable ComplaintStatus complaintStatus) throws Exception {
        if(!auth.isValidUserCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");
        return ResponseEntity.ok(complaintService.getComplaintByStatus(complaintStatus));
    }

    @GetMapping("/{consumerNumber}/complaints")
    public ResponseEntity<?> getComplaintsByConsumerNumber(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode , @PathVariable("consumerNumber") String consumerNumber) throws Exception {
        if(!auth.isValidUserCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");
        return ResponseEntity.ok(complaintService.getComplaintsByConsumerNumber(consumerNumber));
    }

    @GetMapping("/allConsumers")
    public ResponseEntity<?> getAllConsumers(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode) throws Exception {
        if(!auth.isValidUserCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");
        return ResponseEntity.ok(customerService.getAllConsumers());
    }


}
