package com.spring.Springweb.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Name")
    private String name;

    @Size(max = 50)
    @Column(name = "Sku")
    private String sku;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Uom")
    private String uom;

    @Size(max = 100)
    @Column(name = "Category")
    private String category;

    @Size(max = 100)
    @Column(name = "Brand")
    private String brand;

    @Size(max = 255)
    @Column(name = "Description")
    private String description;

    @Column(name = "CostPrice")
    private BigDecimal costPrice;

    @Column(name = "SalePrice")
    private BigDecimal salePrice;

    @Basic(optional = false)
    @NotNull
    @Column(name = "StockQty")
    private BigDecimal stockQty;

    @Column(name = "ReorderLevel")
    private Integer reorderLevel;

    @Column(name = "ExpDate")
    @Temporal(TemporalType.DATE)
    private Date expDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Active")
    private boolean active;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "UpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // ✅ sửa lại mappedBy để khớp với InvoiceItem.product
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Collection<InvoiceItem> invoiceItems;

    // ✅ sửa lại mappedBy để khớp với StockTxn.product
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Collection<StockTxn> stockTxns;

    @JoinColumn(name = "CreatedBy", referencedColumnName = "id")
    @ManyToOne
    private User createdBy;

    @JoinColumn(name = "UpdatedBy", referencedColumnName = "id")
    @ManyToOne
    private User updatedBy;

    // ✅ sửa lại mappedBy để khớp với Review.product
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Collection<Review> reviews;

    @Override
    public String toString() {
        return "Product[id=" + id + ", name=" + name + "]";
    }
}
