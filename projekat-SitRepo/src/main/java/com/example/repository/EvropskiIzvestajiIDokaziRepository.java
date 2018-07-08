package com.example.repository;

import com.example.model.EvropskiIzvestajIDokazi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvropskiIzvestajiIDokaziRepository extends JpaRepository<EvropskiIzvestajIDokazi, Long> {
    EvropskiIzvestajIDokazi save(EvropskiIzvestajIDokazi evropskiIzvestajIDokazi);
    EvropskiIzvestajIDokazi findById(Long id);

    List<EvropskiIzvestajIDokazi> findAllByStatusAndVozacNezgodaManjaId(String status, Long id);
}
