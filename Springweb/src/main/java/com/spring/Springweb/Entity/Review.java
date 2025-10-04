package com.spring.Springweb.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"product", "service", "customer"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "Id")
    private Integer id;

    @NotNull
    @Column(name = "Rating", nullable = false)
    private int rating;

    @Size(max = 255)
    @Column(name = "Comment")
    private String comment;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt", nullable = false)
    private Date createdAt;

    // Liên kết Product
    @ManyToOne
    @JoinColumn(name = "ProductId", referencedColumnName = "Id")
    private Product product;

    // Liên kết Service
    @ManyToOne
    @JoinColumn(name = "ServiceId", referencedColumnName = "Id")
    private ServiceEntity service;

    // Liên kết Customer/User
    @ManyToOne(optional = false)
    @JoinColumn(name = "CustomerId", referencedColumnName = "id")
    private User customer;   // 👈 đổi từ customerId thành customer
}
