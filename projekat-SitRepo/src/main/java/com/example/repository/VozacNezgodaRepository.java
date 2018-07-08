package com.example.repository;

import com.example.model.VozacNezgoda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VozacNezgodaRepository extends JpaRepository<VozacNezgoda, Long> {

    VozacNezgoda save(VozacNezgoda vozacNezgoda);
    List<VozacNezgoda> findBy(Long id);

    @Override
    List<VozacNezgoda> findAll();

  //  VozacNezgoda findByVozacNezgodaIzvestajId(Long id);
}
