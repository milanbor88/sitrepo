package com.example.repository;

import com.example.model.OkolnostiNezgodeB;
import com.example.model.VozacNezgodaManja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OkolnostiNezgodeBRepository extends JpaRepository<OkolnostiNezgodeB, Long>{

    OkolnostiNezgodeB save(OkolnostiNezgodeB okolnostiNezgodeB);
    OkolnostiNezgodeB findByIdB(Long id);
    OkolnostiNezgodeB findByVozacNezgodaManja(VozacNezgodaManja manja);
}
