package com.spring.Springweb.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("CUSTOMER")
@Getter
@Setter
public class Customer extends User {

    private Date dob;
    @Column(name = "notes")
    private String notes;  // thêm trường notes

    @OneToMany(mappedBy = "customer")
    private List<Appointment> appointments;

}
