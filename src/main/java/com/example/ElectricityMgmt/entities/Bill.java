package com.example.ElectricityMgmt.entities;

import com.example.ElectricityMgmt.enums.ConnectionStatus;
import com.example.ElectricityMgmt.enums.ConnectionType;
import com.example.ElectricityMgmt.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "bill")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String billNumber;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID; // Paid or Unpaid

    @Enumerated(EnumType.STRING)
    private ConnectionType connectionType = ConnectionType.DOMESTIC; // Domestic or Commercial

    @Enumerated(EnumType.STRING)
    private ConnectionStatus connectionStatus = ConnectionStatus.DISCONNECTED; // Connected or Disconnected

    @NotEmpty
    @Size(max = 10, message = "Please enter a valid mobile number")
    private String mobileNumber; // Customer mobile number

    @NotNull
    private String billPeriod; // Bill period (e.g., Jan 2025)

    @NotNull
    private LocalDate billDate; // Date when the bill is generated

    @NotNull
    private LocalDate dueDate; // Due date for payment

    @NotNull
    private Double dueAmount; // Amount due on the bill

    @NotNull
    private Double payableAmount; // Amount to be paid

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

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

}
