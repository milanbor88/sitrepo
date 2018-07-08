package com.example.repository;

import com.example.model.VozacNezgodaManja;
import com.example.model.VoziloB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoziloBRepository extends JpaRepository<VoziloB, Long> {
    VoziloB findById(Long id);
    void deleteById(Long id);
    VoziloB findAllByVozacNezgodaManjaId(Long id);

    VoziloB findByVozacNezgodaManja(VozacNezgodaManja manja);

}
