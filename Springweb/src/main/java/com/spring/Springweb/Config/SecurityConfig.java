/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring.Springweb.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // tắt CSRF cho API
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // cho phép tất cả request, không cần login
            );

        return http.build();
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
