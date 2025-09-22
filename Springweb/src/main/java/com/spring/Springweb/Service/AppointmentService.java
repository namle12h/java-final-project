/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Service;

import com.spring.Springweb.Entity.Appointment;
import java.util.List;

public interface AppointmentService {

    Appointment create(Appointment appointment);

    List<Appointment> getByCustomer(Integer customerId);
    List<Appointment> getAll();

    
}
