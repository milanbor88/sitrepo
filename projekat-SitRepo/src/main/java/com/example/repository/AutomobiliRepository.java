package com.example.repository;

import com.example.model.Automobili;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomobiliRepository extends JpaRepository<Automobili, Long> {

        Automobili findByMarka(String marka);

}
