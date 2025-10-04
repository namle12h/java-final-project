package com.spring.Springweb.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "Invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"paymentCollection", "appointment", "customer", "invoiceItemCollection"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "Id")
    private Integer id;

    @NotNull
    @Column(name = "Total", nullable = false)
    private BigDecimal total;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt", nullable = false)
    private Date createdAt;

    // Quan hệ với Payment
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private Collection<Payment> paymentCollection;

    // Quan hệ với Appointment
    @JoinColumn(name = "AppointmentId", referencedColumnName = "Id")
    @ManyToOne
    private Appointment appointment;

    // Quan hệ với Customer/User
    @ManyToOne(optional = false)
    @JoinColumn(name = "CustomerId", referencedColumnName = "id")
    private User customer;   // 👈 Đổi tên từ customerId -> customer

    // Quan hệ với InvoiceItem
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private Collection<InvoiceItem> invoiceItemCollection;

}
