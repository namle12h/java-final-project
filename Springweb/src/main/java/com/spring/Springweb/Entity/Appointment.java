package com.spring.Springweb.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.*;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Appointment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"invoiceCollection", "customer", "staff", "room"}) // tránh vòng lặp khi in
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "Id")
    private Integer id;

    @NotNull
    @Column(name = "StartAt", nullable = false)
    private LocalDateTime startAt;

    @NotNull
    @Column(name = "EndAt", nullable = false)
    private LocalDateTime endAt;

    @NotNull
    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;

    // Khách hàng
    @ManyToOne
    @JoinColumn(name = "CustomerId", nullable = false)
    @JsonIgnore
    private Customer customer;

    // Nhân viên phụ trách
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "StaffId")
    private Staff staff;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    @Column(name = "Notes", length = 255)
    private String notes;

     private String contactName;   // snapshot contact
    private String contactEmail;
    private String contactPhone;

    // Liên kết tới phòng
    @ManyToOne
    @JoinColumn(name = "RoomId")
    @JsonIgnore
    private Room room;

        // Liên kết tới phòng
    @ManyToOne
    @JoinColumn(name = "serviceId")
    @JsonIgnore
    private ServiceEntity service;
    
    @OneToMany(mappedBy = "appointment")
    @JsonIgnore
    private Collection<Invoice> invoiceCollection;

}
