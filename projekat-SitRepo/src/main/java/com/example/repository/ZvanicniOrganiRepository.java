package com.example.repository;

import com.example.model.VozacNezgodaManja;
import com.example.model.ZvanicniOrgani;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZvanicniOrganiRepository extends JpaRepository<ZvanicniOrgani, Long> {

    ZvanicniOrgani findById(Long id);
    ZvanicniOrgani findByVozacNezgodaManja(VozacNezgodaManja manja);

}
