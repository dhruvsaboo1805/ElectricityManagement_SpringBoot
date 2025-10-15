package com.example.ElectricityMgmt.entities;

import com.example.ElectricityMgmt.enums.ElectricalSectionType;
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
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String fullName;

    @Column(nullable = false)
    private String address;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(max = 10, message = "Please enter a valid mobile number")
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private ElectricalSectionType electricalSection;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "customer"  , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Consumer> consumers;

    // add complaint too todo to do

}
