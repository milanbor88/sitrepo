package com.example.repository;

import com.example.model.PovredjeniVanVozila;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PovredjeniVanVozilaRepository extends JpaRepository<PovredjeniVanVozila, Long>{

    PovredjeniVanVozila save(PovredjeniVanVozila povredjeniVanVozila);

    List<PovredjeniVanVozila> findAllByVozacNezgodaIzvestajId(Long id);
}
