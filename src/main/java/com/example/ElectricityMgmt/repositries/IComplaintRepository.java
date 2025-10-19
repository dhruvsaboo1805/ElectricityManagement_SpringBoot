package com.example.ElectricityMgmt.repositries;

import com.example.ElectricityMgmt.dto.ComplaintResponseDTO;
import com.example.ElectricityMgmt.entities.Complaint;
import com.example.ElectricityMgmt.enums.ComplaintStatus;
import com.example.ElectricityMgmt.enums.ComplaintType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IComplaintRepository extends JpaRepository<Complaint,Long> {
List<Complaint> findByComplaintNumber(String consumerNumber);

List<Complaint> findByComplaintType(ComplaintType complaintType);

List<Complaint> findByComplaintStatus(ComplaintStatus complaintStatus);
}
