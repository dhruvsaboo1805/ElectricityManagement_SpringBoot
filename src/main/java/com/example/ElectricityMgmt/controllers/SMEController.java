package com.example.ElectricityMgmt.controllers;

import com.example.ElectricityMgmt.config.Auth;
import com.example.ElectricityMgmt.dto.AdminSMERequestDTO;
import com.example.ElectricityMgmt.dto.AdminSMEResponseDTO;
import com.example.ElectricityMgmt.dto.ComplaintResponseDTO;
import com.example.ElectricityMgmt.enums.ComplaintStatus;
import com.example.ElectricityMgmt.enums.ComplaintType;
import com.example.ElectricityMgmt.services.IComplaintService;
import com.example.ElectricityMgmt.services.ICustomerService;
import com.example.ElectricityMgmt.services.SMEService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*" , methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/sme")
@RequiredArgsConstructor
public class SMEController {
    private final Auth auth;
    private final IComplaintService complaintService;
    private final SMEService smeService;


//    @PostMapping("/register")
//    public ResponseEntity<?> createSME(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode , @RequestBody AdminSMERequestDTO adminSMERequestDTO) throws Exception {
//        AdminSMEResponseDTO Dto = smeService.createSME(adminSMERequestDTO);
//        if(Dto != null) {
//            return ResponseEntity.ok(Dto);
//        } else {
//            return ResponseEntity.status(401).body("Unauthorized Access!!");
//        }
//    }

    @GetMapping("/complaints/allcomplaints")
    public ResponseEntity<?> getComplaintsByComplaintNumber(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode) throws Exception{
        if(!auth.isValidSmeCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @PostMapping("/complaints/{id}/changeStatus")
    public ResponseEntity<?> changeStatus(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode , @PathVariable Long id, @RequestBody ComplaintStatus complaintStatus){
        if(!auth.isValidSmeCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");
        return ResponseEntity.ok(complaintService.changeStatus(complaintStatus , id));
    }

    @PostMapping("/complaints/byComplaintType")
    public ResponseEntity<?> getComplaintByType(@RequestHeader("Username") String username, @RequestHeader("Authorization") String authCode , @RequestBody ComplaintType complaintType){
        if(!auth.isValidSmeCode(username,authCode))
            return ResponseEntity.status(401).body("Unauthorized Access!!");
        return ResponseEntity.ok(complaintService.getComplaintByType(complaintType));
    }



}
