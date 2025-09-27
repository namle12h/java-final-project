package com.spring.Springweb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.Springweb.Entity.ServiceEntity;
import com.spring.Springweb.Service.ServiceManager;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceManager serviceManager;

    // Lấy tất cả dịch vụ
    @GetMapping
    //    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PreAuthorize("hasRole('ADMIN')")


    public List<ServiceEntity> getAllServices() {
        return serviceManager.getAllServices();
    }

    // Lấy dịch vụ theo ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ServiceEntity getServiceById(@PathVariable Integer id) {
        return serviceManager.getServiceById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id " + id));
    }

    // Tạo mới dịch vụ
    @PostMapping
    public ServiceEntity createService(@RequestBody ServiceEntity service) {
        return serviceManager.createService(service);
    }

    // Cập nhật dịch vụ
    @PutMapping("/{id}")
    public ServiceEntity updateService(@PathVariable Integer id, @RequestBody ServiceEntity serviceDetails) {
        return serviceManager.updateService(id, serviceDetails);
    }

    // Xóa dịch vụ
    @DeleteMapping("/{id}")
    public String deleteService(@PathVariable Integer id) {
        serviceManager.deleteService(id);
        return "Service with ID " + id + " has been deleted";
    }
}
