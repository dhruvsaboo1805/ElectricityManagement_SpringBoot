package com.example.ElectricityMgmt.repositries;

import com.example.ElectricityMgmt.entities.Bill;
import com.example.ElectricityMgmt.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IBillRepository extends JpaRepository<Bill , Long> {
    List<Bill> findByConsumerId(Long consumerId);

    List<Bill> findByPaymentStatus(PaymentStatus  paymentStatus);
}
