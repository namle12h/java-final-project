/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.spring.Springweb.Dao.CustomerRepository;
import com.spring.Springweb.Entity.Customer;

@Component
public class ValidationCustomer {

    // Regex chuẩn cho số điện thoại VN (10 số, bắt đầu bằng 0 hoặc +84)
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(0\\d{9}|\\+84\\d{9})$");

    // Regex email
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private final CustomerRepository customerRepository;

    public ValidationCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<String> validate(Customer customer) {
        List<String> errors = new ArrayList<>();

        // validate name
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            errors.add("Tên khách hàng không được để trống");
        } else if (customer.getName().length() > 200) {
            errors.add("Tên tối đa 200 ký tự");
        }

        // validate phone
        if (customer.getPhone() == null || customer.getPhone().trim().isEmpty()) {
            errors.add("Số điện thoại không được để trống");
        } else if (!PHONE_PATTERN.matcher(customer.getPhone()).matches()) {
            errors.add("Số điện thoại không hợp lệ (phải 10 số hoặc +84xxxxxxxxx)");
        } else {
            customerRepository.findByPhone((customer.getPhone())).ifPresent(exist -> {
                if (customer.getId() == null || !exist.getId().equals(customer.getId())) {
                    errors.add("số điện thoại đã tồn tại");
                }

            });
        }

        // validate email
        if (customer.getEmail() != null && !customer.getEmail().isEmpty()) {
            if (!EMAIL_PATTERN.matcher(customer.getEmail()).matches()) {
                errors.add("Email không hợp lệ");
            }
        }

        // validate notes
        if (customer.getNotes() != null && customer.getNotes().length() > 500) {
            errors.add("Ghi chú tối đa 500 ký tự");
        }

        // validate dob (ví dụ không cho ngày sinh ở tương lai)
        if (customer.getDob() != null && customer.getDob().after(new java.util.Date())) {
            errors.add("Ngày sinh không được lớn hơn ngày hiện tại");
        }

        return errors;
    }
}
