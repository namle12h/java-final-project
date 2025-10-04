package com.spring.Springweb.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.Springweb.Entity.User;
import com.spring.Springweb.Repository.UserRepository;
import com.spring.Springweb.util.JwtUtil;

import jakarta.persistence.DiscriminatorValue;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            return ResponseEntity.badRequest().body("Sai email hoặc mật khẩu");
        }

        // sinh token từ userName
        // String token = jwtUtil.generateToken(user.getUsername());
        String token = jwtUtil.generateToken(
        Map.of("role", user.getRole()),  // thêm claim role
        user.getUsername()
);


        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/get-profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Thiếu token");
        }

        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        return ResponseEntity.ok(Map.of(
                "accessToken", token,
                "refreshToken", "",
                "user", Map.of(
                        "id", user.getId(),
                        "email", user.getEmail(),
                        "username", user.getUsername(),
                        "name",user.getName(),
                        "phone",user.getPhone(),
                        "role", user.getClass().getAnnotation(DiscriminatorValue.class).value() // ADMIN, STAFF, CUSTOMER
                )
        ));
    }
    
    
    
}
 