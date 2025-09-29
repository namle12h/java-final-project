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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author ADMIN
 */
@Entity(name = "Service")
@Table(name = "Service")
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findById", query = "SELECT s FROM Service s WHERE s.id = :id"),
    @NamedQuery(name = "Service.findByName", query = "SELECT s FROM Service s WHERE s.name = :name"),
    @NamedQuery(name = "Service.findByPrice", query = "SELECT s FROM Service s WHERE s.price = :price"),
    @NamedQuery(name = "Service.findByDurationMin", query = "SELECT s FROM Service s WHERE s.durationMin = :durationMin"),
    @NamedQuery(name = "Service.findByActive", query = "SELECT s FROM Service s WHERE s.active = :active")})
public class ServiceEntity implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DurationMin")
    private int durationMin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Active")
    private boolean active;

    @Column(length = 1000, name = "Description")
    private String description;

    @Column(length = 255, name = "ImageUrl")
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceId")
    private Collection<ServiceSection> serviceSectionCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @OneToMany(mappedBy = "serviceId")
    private Collection<InvoiceItem> invoiceItemCollection;
    @OneToMany(mappedBy = "serviceId")
    private Collection<Review> reviewCollection;

    public ServiceEntity() {
    }

    public ServiceEntity(Integer id) {
        this.id = id;
    }

    public ServiceEntity(Integer id, String name, BigDecimal price, int durationMin, boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.durationMin = durationMin;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<InvoiceItem> getInvoiceItemCollection() {
        return invoiceItemCollection;
    }

    public void setInvoiceItemCollection(Collection<InvoiceItem> invoiceItemCollection) {
        this.invoiceItemCollection = invoiceItemCollection;
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
        if (!(object instanceof ServiceEntity)) {
            return false;
        }
        ServiceEntity other = (ServiceEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spring.Springweb.Entity.Service[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Collection<ServiceSection> getServiceSectionCollection() {
        return serviceSectionCollection;
    }

    public void setServiceSectionCollection(Collection<ServiceSection> serviceSectionCollection) {
        this.serviceSectionCollection = serviceSectionCollection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
