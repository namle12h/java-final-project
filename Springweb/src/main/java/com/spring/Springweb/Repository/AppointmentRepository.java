/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.Springweb.Entity.Appointment;
import com.spring.Springweb.Entity.Customer;
import com.spring.Springweb.Entity.ServiceEntity;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    // Tìm theo customerId
    List<Appointment> findByCustomer_Id(Integer customerId);

    // Tìm theo staffId
    List<Appointment> findByStaff_Id(Integer staffId);

    // Tìm theo status
    List<Appointment> findByStatus(String status);

    // Tìm số lượng lịch hẹn theo khách + dịch vụ trong khoảng thời gian
    long countByCustomerAndServiceAndStartAtBetween(
            Customer customer,
            ServiceEntity service,
            LocalDateTime start,
            LocalDateTime end
    );
}
