/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Service;

import com.spring.Springweb.Repository.ServiceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.Springweb.Entity.ServiceEntity;
import javax.management.ServiceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Service
@RequiredArgsConstructor
public class ServiceManagerImpl implements ServiceManager {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<ServiceEntity> getServiceById(Integer id) {
        return serviceRepository.findById(id);
    }

    @Override
    public ServiceEntity createService(ServiceEntity service) {
        return serviceRepository.save(service);
    }

    @Override
    @Transactional
    public ServiceEntity updateService(Integer id, ServiceEntity serviceDetails) {
        return serviceRepository.findById(id).map(service -> {
            service.setName(serviceDetails.getName());
            service.setPrice(serviceDetails.getPrice());
            service.setDurationMin(serviceDetails.getDurationMin());
            service.setActive(serviceDetails.getActive());
            return serviceRepository.save(service);
        }).orElseThrow(() -> new RuntimeException("Service not found with id " + id));
    }

    @Override
    public void deleteService(Integer id) {
        try {
            if (!serviceRepository.existsById(id)) {
                throw new ServiceNotFoundException("Service not found with id " + id);
            }
            serviceRepository.deleteById(id);
        } catch (ServiceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
