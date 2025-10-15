package com.example.ElectricityMgmt.repositries;

import com.example.ElectricityMgmt.entities.User;
import com.example.ElectricityMgmt.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    List<User> findByRole(RoleType role);
}
