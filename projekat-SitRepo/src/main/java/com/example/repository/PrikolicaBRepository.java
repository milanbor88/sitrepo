package com.example.repository;

import com.example.model.Prikolica;
import com.example.model.PrikolicaB;
import com.example.model.UgovaracOsiguranjaB;
import com.example.model.VozacNezgodaManja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrikolicaBRepository extends JpaRepository<PrikolicaB, Long> {

    PrikolicaB findById(Long id);
    void deleteById(Long id);

    PrikolicaB findAllByVozacNezgodaManjaId(Long id);

    PrikolicaB findByVozacNezgodaManja(VozacNezgodaManja manja);

}
