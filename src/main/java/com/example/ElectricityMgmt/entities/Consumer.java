package com.example.ElectricityMgmt.entities;

import com.example.ElectricityMgmt.enums.ConnectionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "consumer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Consumer Number is required")
    @Size(min = 10, max = 13, message = "Consumer Number should be between 10 and 13 characters")
    private String consumerNumber;

    @Enumerated(EnumType.STRING)
    private ConnectionType connectionType;

//    @NotBlank(message = "Address is required and cannot be empty.")
//    @Column(nullable = false)
//    private String address;

    @Size(max = 10, message = "Please enter a valid mobile number")
    private String mobileNumber;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "consumer" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Bill> bills;

    @OneToMany(mappedBy = "consumer",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Complaint> complaints;

    private boolean isConnected = true;
}
