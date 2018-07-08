package com.example.repository;

import com.example.model.OkolnostiNezgode;
import com.example.model.VozacNezgodaManja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OkolnostiNezgodeRepository extends JpaRepository<OkolnostiNezgode, Long>{

    OkolnostiNezgode save(OkolnostiNezgode okolnostiNezgode);
    OkolnostiNezgode findById(Long id);
    OkolnostiNezgode findByVozacNezgodaManja(VozacNezgodaManja manja);
}
