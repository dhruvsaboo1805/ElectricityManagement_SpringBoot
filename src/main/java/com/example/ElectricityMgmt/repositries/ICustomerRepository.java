package com.example.ElectricityMgmt.repositries;
import com.example.ElectricityMgmt.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

}
