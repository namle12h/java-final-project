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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "ServiceSection")
@NamedQueries({
    @NamedQuery(name = "ServiceSection.findAll", query = "SELECT s FROM ServiceSection s"),
    @NamedQuery(name = "ServiceSection.findById", query = "SELECT s FROM ServiceSection s WHERE s.id = :id"),
    @NamedQuery(name = "ServiceSection.findByTitle", query = "SELECT s FROM ServiceSection s WHERE s.title = :title"),
    @NamedQuery(name = "ServiceSection.findBySortOrder", query = "SELECT s FROM ServiceSection s WHERE s.sortOrder = :sortOrder")})
public class ServiceSection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "Title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SortOrder")
    private int sortOrder;
    @JoinColumn(name = "SectionType", referencedColumnName = "Code")
    @ManyToOne(optional = false)
    private SectionType sectionType;
    @JoinColumn(name = "ServiceId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private ServiceEntity serviceId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionId")
    private Collection<ServiceSectionItem> serviceSectionItemCollection;

    public ServiceSection() {
    }

    public ServiceSection(Integer id) {
        this.id = id;
    }

    public ServiceSection(Integer id, int sortOrder) {
        this.id = id;
        this.sortOrder = sortOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    public ServiceEntity getServiceId() {
        return serviceId;
    }

    public void setServiceId(ServiceEntity serviceId) {
        this.serviceId = serviceId;
    }

    public Collection<ServiceSectionItem> getServiceSectionItemCollection() {
        return serviceSectionItemCollection;
    }

    public void setServiceSectionItemCollection(Collection<ServiceSectionItem> serviceSectionItemCollection) {
        this.serviceSectionItemCollection = serviceSectionItemCollection;
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
        if (!(object instanceof ServiceSection)) {
            return false;
        }
        ServiceSection other = (ServiceSection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spring.Springweb.Entity.ServiceSection[ id=" + id + " ]";
    }
    
}
