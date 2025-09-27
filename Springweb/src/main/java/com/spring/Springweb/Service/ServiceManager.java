/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Service;

import com.spring.Springweb.Entity.ServiceEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ServiceManager {

    public List<ServiceEntity> getAllServices();

    public Optional<ServiceEntity> getServiceById(Integer id);

    public ServiceEntity createService(ServiceEntity service);

    public ServiceEntity updateService(Integer id, ServiceEntity serviceDetails);

    public void deleteService(Integer id);
}
