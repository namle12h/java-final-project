/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.Springweb.DTO.AppointmentRequest;
import com.spring.Springweb.DTO.AppointmentResponse;
import com.spring.Springweb.Entity.Appointment;
import com.spring.Springweb.Entity.Customer;
import com.spring.Springweb.Entity.ServiceEntity;
import com.spring.Springweb.Entity.User;
import com.spring.Springweb.Repository.AppointmentRepository;
import com.spring.Springweb.Repository.ServiceRepository;
import com.spring.Springweb.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    private final AuditLogService auditLogService;

    private final ServiceRepository serviceRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AppointmentResponse createAppointment(AppointmentRequest req) {
        Customer customer;

        // Nếu có customerId thì lấy trực tiếp từ DB
        if (req.getCustomerId() != null) {
            customer = (Customer) userRepository.findById(req.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with id " + req.getCustomerId()));
        } else {
            // Nếu chưa login thì xử lý guest
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            boolean isLoggedIn = auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal());

            if (isLoggedIn) {
                Object principal = auth.getPrincipal();
                String email = (principal instanceof org.springframework.security.core.userdetails.UserDetails)
                        ? ((org.springframework.security.core.userdetails.UserDetails) principal).getUsername()
                        : ((User) principal).getEmail();

                customer = (Customer) userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Logged in user not found in DB"));
            } else {
                // Guest booking logic
                if (userRepository.findByEmail(req.getEmail()).isPresent()
                        || userRepository.findByPhone(req.getPhone()).isPresent()) {
                    throw new RuntimeException("Email hoặc số điện thoại đã tồn tại, vui lòng nhập lại");
                }

                Customer newCust = new Customer();
                newCust.setName(req.getName());
                newCust.setPhone(req.getPhone());
                newCust.setEmail(req.getEmail());
                newCust.setPasswordHash(passwordEncoder.encode(req.getPhone()));
                newCust.setUsername(req.getEmail());
                newCust.setNotes("Guest booking");
                newCust.setCreatedAt(LocalDateTime.now());
                newCust.setRole("CUSTOMER");
                customer = userRepository.save(newCust);
            }
        }

        // Xử lý thời gian
        LocalDateTime startAt = LocalDateTime.parse(req.getDate() + "T" + req.getTime());
        LocalDateTime endAt = startAt.plusMinutes(60);

        // Lấy dịch vụ
        ServiceEntity service = serviceRepository.findById(req.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found: " + req.getServiceId()));

        // 5. Check duplicate từ DB
        long existing = appointmentRepository.countByCustomerAndServiceAndStartAtBetween(
                customer, service,
                startAt.minusHours(1),
                startAt.plusHours(1)
        );

        if (existing > 0) {
            throw new RuntimeException("Bạn chỉ được đặt 1 lần dịch vụ này trong vòng 1 giờ.");
        }

        // Tạo lịch hẹn
        Appointment appt = new Appointment();
        appt.setCustomer(customer);
        appt.setService(service);
        appt.setStartAt(startAt);
        appt.setEndAt(endAt);
        appt.setNotes(req.getNotes());
        appt.setStatus("Pending");
        appt.setContactEmail(req.getEmail());
        appt.setContactName(req.getName());
        appt.setContactPhone(req.getPhone());
        appt.setCreatedAt(LocalDateTime.now());

        appointmentRepository.save(appt);

        auditLogService.logCreate("Appointment", appt.getId().longValue(), customer.getId().longValue());

        return mapToResponse(appt);
    }

    private AppointmentResponse mapToResponse(Appointment a) {
        AppointmentResponse res = new AppointmentResponse();
        res.setId(a.getId());
        res.setContactName(a.getContactName());
        res.setContactEmail(a.getContactEmail());
        res.setContactPhone(a.getContactPhone());
        res.setStatus(a.getStatus());
        res.setNotes(a.getNotes());
        res.setStartAt(a.getStartAt());
        res.setEndAt(a.getEndAt());
        res.setServiceName(a.getService() != null ? a.getService().getName() : null);
        res.setStaffName(a.getStaff() != null ? a.getStaff().getName() : null);
        res.setRoomName(a.getRoom() != null ? a.getRoom().getName() : null);
        return res;
    }

    @Override
    public Optional<Appointment> getAppointmentbyId(Integer customerId) {
        return appointmentRepository.findById(customerId);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment updateAppointment(Integer id, Appointment appointmentDetails) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Long performedBy = existing.getCustomer() != null
                ? existing.getCustomer().getId().longValue()
                : null;

        // So sánh từng field, nếu khác thì update + log
        if (appointmentDetails.getStartAt() != null && !appointmentDetails.getStartAt().equals(existing.getStartAt())) {
            auditLogService.logUpdate("Appointment", id.longValue(), "StartAt",
                    existing.getStartAt().toString(), appointmentDetails.getStartAt().toString(), performedBy);
            existing.setStartAt(appointmentDetails.getStartAt());
        }

        if (appointmentDetails.getEndAt() != null && !appointmentDetails.getEndAt().equals(existing.getEndAt())) {
            auditLogService.logUpdate("Appointment", id.longValue(), "EndAt",
                    existing.getEndAt().toString(), appointmentDetails.getEndAt().toString(), performedBy);
            existing.setEndAt(appointmentDetails.getEndAt());
        }

        if (appointmentDetails.getStatus() != null && !appointmentDetails.getStatus().equals(existing.getStatus())) {
            auditLogService.logUpdate("Appointment", id.longValue(), "Status",
                    existing.getStatus(), appointmentDetails.getStatus(), performedBy);
            existing.setStatus(appointmentDetails.getStatus());
        }

        if (appointmentDetails.getNotes() != null && !appointmentDetails.getNotes().equals(existing.getNotes())) {
            auditLogService.logUpdate("Appointment", id.longValue(), "Notes",
                    existing.getNotes(), appointmentDetails.getNotes(), performedBy);
            existing.setNotes(appointmentDetails.getNotes());
        }

        if (appointmentDetails.getStaff() != null && (existing.getStaff() == null
                || !appointmentDetails.getStaff().getId().equals(existing.getStaff().getId()))) {
            auditLogService.logUpdate("Appointment", id.longValue(), "Staff",
                    existing.getStaff() != null ? existing.getStaff().getId().toString() : null,
                    appointmentDetails.getStaff().getId().toString(), performedBy);
            existing.setStaff(appointmentDetails.getStaff());
        }

        if (appointmentDetails.getRoom() != null && (existing.getRoom() == null
                || !appointmentDetails.getRoom().getId().equals(existing.getRoom().getId()))) {
            auditLogService.logUpdate("Appointment", id.longValue(), "Room",
                    existing.getRoom() != null ? existing.getRoom().getId().toString() : null,
                    appointmentDetails.getRoom().getId().toString(), performedBy);
            existing.setRoom(appointmentDetails.getRoom());
        }

        return appointmentRepository.save(existing);
    }

    @Override
    public void deleteAppointment(Integer id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Page<AppointmentResponse> getAllAppointments(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Appointment> appts = appointmentRepository.findAll(pageable);

        // map từng Appointment -> AppointmentResponse
        return appts.map(this::mapToResponse);
    }

}
