/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Config;

import com.spring.Springweb.filter.JwtFilter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf(csrf -> csrf.disable()) // tắt CSRF cho API
    //         .authorizeHttpRequests(auth -> auth
    //             .anyRequest().permitAll() // cho phép tất cả request, không cần login
    //         );
    //     return http.build();
    // }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {
                })
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/api/auth/**", "/api/products/**", "/api/public/**").permitAll()
                .requestMatchers("/api/dashboard/**", "/api/admin/**").authenticated()
                .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // React app
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

// //    private final StaffService staffService;
// //    public SecurityConfig(StaffService staffService) {
// //        this.staffService = staffService;
// //    }
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
//     @Bean
//     DaoAuthenticationProvider authProvider(UserDetailsService uds, PasswordEncoder encoder) {
//         DaoAuthenticationProvider p = new DaoAuthenticationProvider();
//         p.setUserDetailsService(uds);
//         p.setPasswordEncoder(encoder);
//         return p;
//     }
//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
// //                .csrf().disable()
//                 .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/login", "/register", "/css/**", "/js/**", "/api/**").permitAll()
//                 .anyRequest().authenticated()
//                 )
//                 .formLogin(form -> form
//                 .loginPage("/login") // GET login form
//                 .loginProcessingUrl("/login") // POST login handled by Spring Security
//                 .usernameParameter("email") // name field in form
//                 .passwordParameter("password") // name field in form
//                 .defaultSuccessUrl("/home", true)
//                 .permitAll()
//                 )
//                 .logout(logout -> logout
//                 .logoutUrl("/logout")
//                 .logoutSuccessUrl("/login?logout")
//                 .permitAll()
//                 );
//         return http.build();
//     }
//     @Bean
//     public UserDetailsService userDetailsService() {
//         UserDetails user = User.withUsername("admin")
//                 .password("{noop}123456") // {noop} để không mã hóa password
//                 .roles("USER")
//                 .build();
//         return new InMemoryUserDetailsManager(user);
//     }
}
