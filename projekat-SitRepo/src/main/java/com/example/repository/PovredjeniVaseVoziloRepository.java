package com.example.repository;

import com.example.model.PovredjeniVaseVozilo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PovredjeniVaseVoziloRepository extends JpaRepository<PovredjeniVaseVozilo, Long> {

    PovredjeniVaseVozilo save(PovredjeniVaseVozilo povredjeniVaseVozilo);

    List<PovredjeniVaseVozilo> findAllByVozacNezgodaIzvestajId(Long id);
}
