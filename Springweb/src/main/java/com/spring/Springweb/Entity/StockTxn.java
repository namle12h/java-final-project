/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "StockTxn")
@NamedQueries({
    @NamedQuery(name = "StockTxn.findAll", query = "SELECT s FROM StockTxn s"),
    @NamedQuery(name = "StockTxn.findById", query = "SELECT s FROM StockTxn s WHERE s.id = :id"),
    @NamedQuery(name = "StockTxn.findByTxnType", query = "SELECT s FROM StockTxn s WHERE s.txnType = :txnType"),
    @NamedQuery(name = "StockTxn.findByQty", query = "SELECT s FROM StockTxn s WHERE s.qty = :qty"),
    @NamedQuery(name = "StockTxn.findByCreatedAt", query = "SELECT s FROM StockTxn s WHERE s.createdAt = :createdAt"),
    @NamedQuery(name = "StockTxn.findByNotes", query = "SELECT s FROM StockTxn s WHERE s.notes = :notes")})
public class StockTxn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TxnType")
    private String txnType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Qty")
    private BigDecimal qty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Size(max = 200)
    @Column(name = "Notes")
    private String notes;
    @JoinColumn(name = "ProductId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Product productId;

    public StockTxn() {
    }

    public StockTxn(Integer id) {
        this.id = id;
    }

    public StockTxn(Integer id, String txnType, BigDecimal qty, Date createdAt) {
        this.id = id;
        this.txnType = txnType;
        this.qty = qty;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
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
        if (!(object instanceof StockTxn)) {
            return false;
        }
        StockTxn other = (StockTxn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spring.Springweb.Entity.StockTxn[ id=" + id + " ]";
    }
    
}
