/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.Springweb.Dao.AppointmentRepository;
import com.spring.Springweb.Entity.Appointment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment create(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getByCustomer(Integer customerId) {
        return appointmentRepository.findByCustomerId_Id(customerId);
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

}
