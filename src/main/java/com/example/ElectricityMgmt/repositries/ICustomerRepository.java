package com.example.ElectricityMgmt.repositries;
import com.example.ElectricityMgmt.entities.Consumer;
import com.example.ElectricityMgmt.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

}
