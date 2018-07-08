package com.example.repository;

import com.example.model.PovredjeniDrugoVozilo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PovredjeniDrugoVozilosRepository extends JpaRepository<PovredjeniDrugoVozilo, Long> {

    PovredjeniDrugoVozilo save(PovredjeniDrugoVozilo povredjeniDrugoVozilo);
    List<PovredjeniDrugoVozilo> findAllByVozacNezgodaIzvestajId(Long id);

}
