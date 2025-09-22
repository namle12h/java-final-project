
package com.spring.Springweb.Dao;

import com.spring.Springweb.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Staff findByEmail (String email);
    
    boolean existsByEmail(String email);
}
