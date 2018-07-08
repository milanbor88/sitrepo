package com.example.repository;

import com.example.model.OsiguranjeB;
import com.example.model.UgovaracOsiguranjaB;
import com.example.model.VozacNezgodaManja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OsiguranjeBRepository extends JpaRepository<OsiguranjeB, Long> {

    OsiguranjeB findById(Long id);
    void deleteById(Long id);

    List<OsiguranjeB> findAllByVozacNezgodaManjaId(Long id);
    OsiguranjeB findByVozacNezgodaManja(VozacNezgodaManja manja);
}
