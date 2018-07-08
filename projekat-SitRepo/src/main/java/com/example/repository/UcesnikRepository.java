package com.example.repository;

import com.example.model.Ucesnik;
import com.example.model.VozacNezgodaManja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UcesnikRepository extends JpaRepository<Ucesnik, Long> {

    Ucesnik save(Ucesnik ucesnik);
    void deleteById(Long id);
    Ucesnik findById(Long id);

    Ucesnik findUcesnikByVozacNezgodaManjaId(Long id);
    Ucesnik findByVozacNezgodaManja(VozacNezgodaManja manja);


}
