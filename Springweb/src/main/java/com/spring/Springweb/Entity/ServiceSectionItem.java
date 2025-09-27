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
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "ServiceSectionItem")
@NamedQueries({
    @NamedQuery(name = "ServiceSectionItem.findAll", query = "SELECT s FROM ServiceSectionItem s"),
    @NamedQuery(name = "ServiceSectionItem.findById", query = "SELECT s FROM ServiceSectionItem s WHERE s.id = :id"),
    @NamedQuery(name = "ServiceSectionItem.findByTitle", query = "SELECT s FROM ServiceSectionItem s WHERE s.title = :title"),
    @NamedQuery(name = "ServiceSectionItem.findByDescription", query = "SELECT s FROM ServiceSectionItem s WHERE s.description = :description"),
    @NamedQuery(name = "ServiceSectionItem.findByImageUrl", query = "SELECT s FROM ServiceSectionItem s WHERE s.imageUrl = :imageUrl"),
    @NamedQuery(name = "ServiceSectionItem.findByExtraOrder", query = "SELECT s FROM ServiceSectionItem s WHERE s.extraOrder = :extraOrder")})
public class ServiceSectionItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "Title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "Description")
    private String description;
    @Size(max = 500)
    @Column(name = "ImageUrl")
    private String imageUrl;
    @Column(name = "ExtraOrder")
    private Integer extraOrder;
    @JoinColumn(name = "SectionId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private ServiceSection sectionId;

    public ServiceSectionItem() {
    }

    public ServiceSectionItem(Integer id) {
        this.id = id;
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

    public Integer getExtraOrder() {
        return extraOrder;
    }

    public void setExtraOrder(Integer extraOrder) {
        this.extraOrder = extraOrder;
    }

    public ServiceSection getSectionId() {
        return sectionId;
    }

    public void setSectionId(ServiceSection sectionId) {
        this.sectionId = sectionId;
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
        if (!(object instanceof ServiceSectionItem)) {
            return false;
        }
        ServiceSectionItem other = (ServiceSectionItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spring.Springweb.Entity.ServiceSectionItem[ id=" + id + " ]";
    }
    
}
