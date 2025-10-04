package com.spring.Springweb.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"appointmentCollection", "appointmentCollection1", "invoiceCollection", "productCollection", "productCollection1", "reviewCollection"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 20)
    @Column(name = "phone")
    private String phone;

    @Size(max = 100)
    @Column(name = "email")
    private String email;

    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "hire_date")
    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @Size(max = 20)
    @Column(name = "status")
    private String status;

    @NotNull
    @Size(min = 1, max = 20)
    // @Column(name = "role", nullable = false)
    @Column(name = "role", insertable = false, updatable = false)
    private String role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // @Column(name = "notes", length = 2147483647)
    // private String notes;

    // Quan hệ với Appointment
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Appointment> appointmentCollection;

    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private Collection<Appointment> appointmentCollection1;

    // Quan hệ với Invoice
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Invoice> invoiceCollection;

    // Quan hệ với Product
    @OneToMany(mappedBy = "createdBy")
    @JsonIgnore
    private Collection<Product> productCollection;

    @OneToMany(mappedBy = "updatedBy")
    @JsonIgnore
    private Collection<Product> productCollection1;

    // Quan hệ với Review
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Review> reviewCollection;

    @Column(length = 255, name = "password_hash")
    @JsonIgnore
    private String passwordHash;

    @Column(name = "username")
    private String username;

    public String getRoleValue() {
        DiscriminatorValue dv = this.getClass().getAnnotation(DiscriminatorValue.class);
        return (dv != null) ? dv.value() : "UNKNOWN";
    }

    public enum Role {
        ADMIN, STAFF, CUSTOMER
    }
}
