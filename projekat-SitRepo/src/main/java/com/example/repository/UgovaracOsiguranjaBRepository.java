package com.example.repository;

import com.example.model.UgovaracOsiguranja;
import com.example.model.UgovaracOsiguranjaB;
import com.example.model.VozacNezgodaManja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UgovaracOsiguranjaBRepository extends JpaRepository<UgovaracOsiguranjaB, Long>{

    UgovaracOsiguranjaB findById(Long id);
    void deleteById(Long id);
    UgovaracOsiguranjaB findAllByVozacNezgodaManjaId(Long id);

    UgovaracOsiguranjaB findByVozacNezgodaManja(VozacNezgodaManja manja);

}
