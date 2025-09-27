//package com.spring.Springweb.Controller;
//
//import com.spring.Springweb.Entity.Customer;
//import com.spring.Springweb.Entity.User;
//import com.spring.Springweb.Service.CustomerService;
//import com.spring.Springweb.Service.UserService;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//    private final CustomerService customerService;
//
//    // 🔹 Tạo Customer
//    @PostMapping("/customers")
//    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
//        customer.setRole(User.Role.CUSTOMER);
//        if (customer.getDob() == null) {
//            throw new RuntimeException("Customer phải có ngày sinh (dob)");
//        }
//        return ResponseEntity.ok(customerService.create(customer));
//    }
//
//    // 🔹 Admin/Staff xem danh sách tất cả customer
//    @GetMapping("/customers")
//    public ResponseEntity<List<User>> getAllCustomers() {
//        return ResponseEntity.ok(userService.getAllByRole(Users.Role.CUSTOMER));
//    }
//
//    // 🔹 Tạo Staff
//    @PostMapping("/staff")
//    public ResponseEntity<User> createStaff(@RequestBody User user) {
//        user.setRole(User.Role.STAFF);
//        if (user.getHireDate() == null) {
//            throw new RuntimeException("Staff phải có ngày vào làm (hireDate)");
//        }
//        return ResponseEntity.ok(userService.createUser(user));
//    }
//
//    // 🔹 Tạo Admin
//    @PostMapping("/admins")
//    public ResponseEntity<Users> createAdmin(@RequestBody Users user) {
//        user.setRole(Users.Role.ADMIN);
//        return ResponseEntity.ok(userService.createUser(user));
//    }
//
//    // 🔹 Lấy danh sách tất cả user
//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//        return ResponseEntity.ok(userService.getAllUsers());
//    }
//
//    // 🔹 Lấy user theo id
//    @GetMapping("/users/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
//        return ResponseEntity.ok(userService.getUser(id));
//    }
//
//    // 🔹 Xóa user
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
//        userService.deleteUser(id);
//        return ResponseEntity.ok().build();
//    }
//}
