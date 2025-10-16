package com.example.ElectricityMgmt.entities;

import com.example.ElectricityMgmt.enums.ComplaintCategory;
import com.example.ElectricityMgmt.enums.ComplaintStatus;
import com.example.ElectricityMgmt.enums.ComplaintType;
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

@Entity
@Table(name="complaint")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String complaintNumber;

    @Enumerated(EnumType.STRING)
    private ComplaintType complaintType=ComplaintType.BILLING_ISSUE;

    @Enumerated(EnumType.STRING)
    private ComplaintCategory complaintCategory=ComplaintCategory.BILL_NOT_RECEIVED;

    @NotNull(message = "landmark is required")
    private String landMark;

    @NotEmpty(message = "Description is Required")
    private String description;

    @NotEmpty(message = "Mobile Number is Required")
    @Size(min = 10,max = 10,message = "Enter valid Mobile Number")
    private String mobileNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant created_at;

    @Column(nullable = false)
    @LastModifiedDate
    private Instant updated_at;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus complaintStatus=ComplaintStatus.PENDING;

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
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;
}
