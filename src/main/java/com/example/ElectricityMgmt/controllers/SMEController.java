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

import java.util.List;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*" , methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/sme")
@RequiredArgsConstructor
public class SMEController {
    private static String authCode = "";
    private static String username = "";
    private final Auth auth;
    private final IComplaintService complaintService;
    private final SMEService smeService;

    @ModelAttribute
    public void init(@RequestHeader("Username") String uname, @RequestHeader("Authorization") String auth){
        username = uname;
        authCode = auth;
    }

    @PostMapping("/register")
    public ResponseEntity<AdminSMEResponseDTO> createSME(@RequestBody AdminSMERequestDTO adminSMERequestDTO) throws Exception {
        AdminSMEResponseDTO Dto = smeService.createSME(adminSMERequestDTO);
        if(Dto != null) {
            return ResponseEntity.ok(Dto);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/complaints/{complaintNumber}/allcomplaints")
    public ResponseEntity<List<ComplaintResponseDTO>> getComplaintsByComplaintNumber(@PathVariable String complaintNumber) throws Exception{
        if(!auth.isValidSmeCode(username,authCode))
            return ResponseEntity.status(401).body(null);
        return ResponseEntity.ok(complaintService.getComplaintsByComplaintNumber(complaintNumber));
    }

    @PostMapping("/complaints/{id}/changeStatus")
    public ResponseEntity<ComplaintResponseDTO> changeStatus(@PathVariable Long id, @RequestBody ComplaintStatus complaintStatus){
        if(!auth.isValidSmeCode(username,authCode))
            return ResponseEntity.status(401).body(null);
        return ResponseEntity.ok(complaintService.changeStatus(complaintStatus , id));
    }

    @PostMapping("/complaints/byComplaintType")
    public ResponseEntity<List<ComplaintResponseDTO>> getComplaintByType(@RequestBody ComplaintType complaintType){
        if(!auth.isValidSmeCode(username,authCode))
            return ResponseEntity.status(401).body(null);
        return ResponseEntity.ok(complaintService.getComplaintByType(complaintType));
    }



}
