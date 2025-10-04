package com.spring.Springweb.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "InvoiceItem")
@Getter
@Setter
@NoArgsConstructor
public class InvoiceItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @NotNull
    @Column(name = "Qty", nullable = false)
    private int qty;

    @NotNull
    @Column(name = "UnitPrice", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "LineTotal")
    private BigDecimal lineTotal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "InvoiceId", referencedColumnName = "Id", nullable = false)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "ProductId", referencedColumnName = "Id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ServiceId", referencedColumnName = "Id")
    private ServiceEntity service;

    public InvoiceItem(Integer id, int qty, BigDecimal unitPrice) {
        this.id = id;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }
}
