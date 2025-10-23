package com.example.ElectricityMgmt.repositries;

import com.example.ElectricityMgmt.entities.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IConsumerRepository extends JpaRepository<Consumer, Long> {
    Optional<Consumer> findByConsumerNumber(String consumerNumber);

    List<Consumer> findByCustomerId(Long customerId);
}
