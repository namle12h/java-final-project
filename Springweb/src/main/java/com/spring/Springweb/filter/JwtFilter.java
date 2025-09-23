/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.filter;


import com.spring.Springweb.Dao.StaffRepository;
import com.spring.Springweb.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final StaffRepository staffRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Lấy header Authorization
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // bỏ "Bearer "

            try {
                // Lấy username từ token
                String username = jwtUtil.extractUsername(token);

                // Nếu username hợp lệ và chưa có auth trong context
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    var staff = staffRepository.findByUsername(username).orElse(null);

                    if (staff != null && jwtUtil.validateToken(token, staff.getUsername())) {
                        // Tạo Authentication
                        UsernamePasswordAuthenticationToken authentication
                                = new UsernamePasswordAuthenticationToken(
                                        staff.getUsername(), null, new ArrayList<>());

                        // Lưu vào SecurityContext
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (Exception e) {
                // Token không hợp lệ thì bỏ qua, không set auth
                System.out.println("JWT invalid: " + e.getMessage());
            }
        }

        // Cho request đi tiếp
        filterChain.doFilter(request, response);
    }
}
