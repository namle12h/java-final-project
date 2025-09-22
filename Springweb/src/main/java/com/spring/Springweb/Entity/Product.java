/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "Product")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findBySku", query = "SELECT p FROM Product p WHERE p.sku = :sku"),
    @NamedQuery(name = "Product.findByUom", query = "SELECT p FROM Product p WHERE p.uom = :uom"),
    @NamedQuery(name = "Product.findByCategory", query = "SELECT p FROM Product p WHERE p.category = :category"),
    @NamedQuery(name = "Product.findByBrand", query = "SELECT p FROM Product p WHERE p.brand = :brand"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name = "Product.findByCostPrice", query = "SELECT p FROM Product p WHERE p.costPrice = :costPrice"),
    @NamedQuery(name = "Product.findBySalePrice", query = "SELECT p FROM Product p WHERE p.salePrice = :salePrice"),
    @NamedQuery(name = "Product.findByStockQty", query = "SELECT p FROM Product p WHERE p.stockQty = :stockQty"),
    @NamedQuery(name = "Product.findByReorderLevel", query = "SELECT p FROM Product p WHERE p.reorderLevel = :reorderLevel"),
    @NamedQuery(name = "Product.findByExpDate", query = "SELECT p FROM Product p WHERE p.expDate = :expDate"),
    @NamedQuery(name = "Product.findByActive", query = "SELECT p FROM Product p WHERE p.active = :active"),
    @NamedQuery(name = "Product.findByCreatedAt", query = "SELECT p FROM Product p WHERE p.createdAt = :createdAt"),
    @NamedQuery(name = "Product.findByUpdatedAt", query = "SELECT p FROM Product p WHERE p.updatedAt = :updatedAt")})
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
    @Size(max = 2147483647)
    @Column(name = "Description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CostPrice")
    private BigDecimal costPrice;
    @Basic(optional = false)
    @NotNull
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
    @OneToMany(mappedBy = "productId")
    private Collection<InvoiceItem> invoiceItemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<StockTxn> stockTxnCollection;
    @OneToMany(mappedBy = "productId")
    private Collection<Review> reviewCollection;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, String uom, BigDecimal salePrice, BigDecimal stockQty, boolean active, Date createdAt) {
        this.id = id;
        this.name = name;
        this.uom = uom;
        this.salePrice = salePrice;
        this.stockQty = stockQty;
        this.active = active;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Collection<InvoiceItem> getInvoiceItemCollection() {
        return invoiceItemCollection;
    }

    public void setInvoiceItemCollection(Collection<InvoiceItem> invoiceItemCollection) {
        this.invoiceItemCollection = invoiceItemCollection;
    }

    public Collection<StockTxn> getStockTxnCollection() {
        return stockTxnCollection;
    }

    public void setStockTxnCollection(Collection<StockTxn> stockTxnCollection) {
        this.stockTxnCollection = stockTxnCollection;
    }

    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<Review> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spring.Springweb.Entity.Product[ id=" + id + " ]";
    }
    
}
