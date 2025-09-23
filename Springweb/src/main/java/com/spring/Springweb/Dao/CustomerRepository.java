/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Dao;

import com.spring.Springweb.Entity.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Có thể thêm custom query nếu cần
    Optional<Customer> findByPhone(String phone);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
  
}
