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
    private static String authCode="";
    private static String username = "";
    private final ICustomerService customerService;
    private final IBillService billService;
    private final IComplaintService complaintService;

    @ModelAttribute
    public void init(@RequestHeader("Username") String uname, @RequestHeader("Authorization") String auth){
        username = uname;
        authCode = auth;
    }

    @GetMapping("/{consumerNumber}/bills")
    public ResponseEntity<List<BillResponseDTO>> getBillByConsumerNumber(@PathVariable("consumerNumber") String consumerNumber){
        if(!auth.isValidUserCode(username,authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(billService.getBillByConsumerNumber(consumerNumber));
    }

    @PostMapping("/bills/{id}/pay")
    public ResponseEntity<BillResponseDTO> payBill(@PathVariable Long id) throws Exception {
        if(!auth.isValidUserCode(username,authCode))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(billService.payBill(id));
    }


    @PostMapping("/bills/{consumerNumber}/paidBills")
    public ResponseEntity<List<BillResponseDTO>> getAllPaidBills(@PathVariable String consumerNumber) throws Exception {
        if(!auth.isValidUserCode(username,authCode))
            return ResponseEntity.status(401).body(null);

        return  ResponseEntity.ok(billService.getAllPaidBills(consumerNumber));
    }

    @PostMapping("/registerComplaint")
    public ResponseEntity<ComplaintResponseDTO> registerComplaint(@RequestBody ComplaintRequestDTO complaintRequestDTO) throws Exception {
        if(!auth.isValidUserCode(username,authCode))
            return ResponseEntity.status(401).body(null);
        return ResponseEntity.ok(complaintService.registerComplaint(complaintRequestDTO));
    }

    @PostMapping("/complaints/byStatus")
    public ResponseEntity<List<ComplaintResponseDTO>> getComplaintByStatus(@RequestBody ComplaintStatus complaintStatus) throws Exception {
        if(!auth.isValidUserCode(username,authCode))
            return ResponseEntity.status(401).body(null);
        return ResponseEntity.ok(complaintService.getComplaintByStatus(complaintStatus));
    }

    @GetMapping("/{consumerNumber}/complaints")
    public ResponseEntity<List<ComplaintResponseDTO>> getComplaintsByConsumerNumber(@PathVariable("consumerNumber") String consumerNumber) throws Exception {
        if(!auth.isValidUserCode(username,authCode))
            return ResponseEntity.status(401).body(null);
        return ResponseEntity.ok(complaintService.getComplaintsByConsumerNumber(consumerNumber));
    }


}
