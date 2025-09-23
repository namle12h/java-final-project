/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Controller;

import com.spring.Springweb.Entity.Customer;
import com.spring.Springweb.Service.CustomerService;
import com.spring.Springweb.validation.ValidationCustomer;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // @PostMapping
    // public ResponseEntity<Customer> create(@RequestBody Customer customer) {
    //     return ResponseEntity.ok(customerService.create(customer));
    // }
@PostMapping
public ResponseEntity<?> create(
        @Valid @RequestBody ValidationCustomer customerDto,
        BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        List<String> errors = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }

    Customer customer = new Customer();
    customer.setName(customerDto.getName());
    customer.setPhone(customerDto.getPhone());
    customer.setEmail(customerDto.getEmail());
    customer.setNotes(customerDto.getNotes());

    // Convert LocalDate -> java.sql.Date
    if (customerDto.getDob() != null) {
        customer.setDob(java.sql.Date.valueOf(customerDto.getDob()));
    }

    // createdAt auto set trong @PrePersist
    return ResponseEntity.ok(customerService.create(customer));
}


    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Integer id, @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.update(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }
}
