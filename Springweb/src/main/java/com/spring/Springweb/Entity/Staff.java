package com.spring.Springweb.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@DiscriminatorValue("STAFF")
@Getter
@Setter
public class Staff extends User {

    @Column(name = "hire_date")
    private Date hireDate;
    private String status;

    public void getusername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
