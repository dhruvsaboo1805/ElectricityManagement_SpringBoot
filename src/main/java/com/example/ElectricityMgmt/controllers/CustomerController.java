package com.example.ElectricityMgmt.controllers;

import com.example.ElectricityMgmt.dto.BillResponseDTO;
import com.example.ElectricityMgmt.services.IBillService;
import com.example.ElectricityMgmt.services.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer/")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;
    private final IBillService billService;

    @GetMapping("{consumerNumber}/bills")
    public ResponseEntity<List<BillResponseDTO>> getAllBills(@PathVariable("consumerNumber") String consumerNumber){
        return ResponseEntity.ok(billService.getBillByConsumerNumber(consumerNumber));
    }

    @PostMapping("bills/{id}/pay")
    public ResponseEntity<BillResponseDTO> payBill(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(billService.payBill(id));
    }

    @GetMapping("bills/paidBills")
    public ResponseEntity<List<BillResponseDTO>> getAllPaidBills(){
        return  ResponseEntity.ok(billService.getAllPaidBills());
    }

    //TODO Complaints apis are left


}
