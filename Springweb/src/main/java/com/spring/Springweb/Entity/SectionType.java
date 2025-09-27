/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "SectionType")
@NamedQueries({
    @NamedQuery(name = "SectionType.findAll", query = "SELECT s FROM SectionType s"),
    @NamedQuery(name = "SectionType.findByCode", query = "SELECT s FROM SectionType s WHERE s.code = :code"),
    @NamedQuery(name = "SectionType.findByDescription", query = "SELECT s FROM SectionType s WHERE s.description = :description")})
public class SectionType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Code")
    private String code;
    @Size(max = 200)
    @Column(name = "Description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionType")
    private Collection<ServiceSection> serviceSectionCollection;

    public SectionType() {
    }

    public SectionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<ServiceSection> getServiceSectionCollection() {
        return serviceSectionCollection;
    }

    public void setServiceSectionCollection(Collection<ServiceSection> serviceSectionCollection) {
        this.serviceSectionCollection = serviceSectionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SectionType)) {
            return false;
        }
        SectionType other = (SectionType) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spring.Springweb.Entity.SectionType[ code=" + code + " ]";
    }
    
}
