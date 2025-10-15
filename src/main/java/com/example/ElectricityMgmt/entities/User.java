package com.example.ElectricityMgmt.entities;

import com.example.ElectricityMgmt.enums.ElectricalSectionType;
import com.example.ElectricityMgmt.enums.RoleType;
import com.example.ElectricityMgmt.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username should be between 3 and 50 characters")
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant created_at;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant updated_at;

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.created_at = now;
        this.updated_at = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_at = Instant.now();
    }



}
