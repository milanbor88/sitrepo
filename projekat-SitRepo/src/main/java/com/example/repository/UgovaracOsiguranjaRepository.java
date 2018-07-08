package com.example.repository;

import com.example.model.Osiguranje;
import com.example.model.UgovaracOsiguranja;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UgovaracOsiguranjaRepository extends JpaRepository<UgovaracOsiguranja, Long>{

    UgovaracOsiguranja findById(Long id);
    UgovaracOsiguranja findUgovaracOsiguranjaByUser_Id(Long id);
    void deleteById(Long id);

    UgovaracOsiguranja findUgovaracOsiguranjaByUser(User user);
}
