/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Service;

import com.spring.Springweb.Entity.Customer;
import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer update(Integer id, Customer customer);
    void delete(Integer id);
    Customer getById(Integer id);
    List<Customer> getAll();
}
