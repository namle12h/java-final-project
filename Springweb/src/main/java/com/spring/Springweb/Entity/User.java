package com.spring.Springweb.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 20)
    private String phone;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 255, name = "password_hash")
   @JsonIgnore
    private String passwordHash;

    @Column(name = "username")
    private String username;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, length = 20, updatable = false)
//    private Role role;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public String getRole() {
        DiscriminatorValue dv = this.getClass().getAnnotation(DiscriminatorValue.class);
        return (dv != null) ? dv.value() : "UNKNOWN";
    }

    public enum Role {
        ADMIN, STAFF, CUSTOMER
    }
}
