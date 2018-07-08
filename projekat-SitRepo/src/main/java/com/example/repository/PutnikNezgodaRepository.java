package com.example.repository;

import com.example.model.PutnikNezgoda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PutnikNezgodaRepository extends JpaRepository<PutnikNezgoda, Long> {

    PutnikNezgoda save(PutnikNezgoda putnikNezgoda);
    PutnikNezgoda findById(Long id);


    @Override
    List<PutnikNezgoda> findAll();
}
