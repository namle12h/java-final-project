///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.spring.Springweb.Controller;
//
//import com.spring.Springweb.Repository.StaffRepository;
//import com.spring.Springweb.Entity.Staff;
//import com.spring.Springweb.util.JwtUtil;
//import jakarta.servlet.http.HttpServletRequest;
//import java.util.Map;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final StaffRepository staffRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
//        String email = loginRequest.get("email");
//        String password = loginRequest.get("password");
//
//        // Tìm staff theo email
//        Staff staff = staffRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));
//
//        // Kiểm tra password
//        if (!passwordEncoder.matches(password, staff.getPasswordHash())) {
//            return ResponseEntity.badRequest().body("Sai email hoặc mật khẩu");
//        }
//
//        // Sinh JWT bằng username (unique)
//        String token = jwtUtil.generateToken(staff.getUsername());
//
//        return ResponseEntity.ok(Map.of("token", token));
//    }
//
//    @GetMapping("/get-profile")
//    public ResponseEntity<?> getProfile(HttpServletRequest request) {
//        // Lấy token từ header
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return ResponseEntity.status(401).body("Thiếu token");
//        }
//
//        String token = authHeader.substring(7);
//        String username = jwtUtil.extractUsername(token);
//
//        // Lấy staff từ DB
//        Staff staff = staffRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
//
//        return ResponseEntity.ok(Map.of(
//                "accessToken", token,
//                "refreshToken", "", // nếu chưa có thì để rỗng
//                "user", Map.of(
//                        "id", staff.getId(),
//                        "email", staff.getEmail(),
//                        "username", staff.getUsername()
//                )
//        ));
//    }
//
//   
//
//}
