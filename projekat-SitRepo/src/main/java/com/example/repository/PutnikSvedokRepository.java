package com.example.repository;

import com.example.model.PutnikSvedok;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PutnikSvedokRepository extends JpaRepository<PutnikSvedok, Long> {

    PutnikSvedok save(PutnikSvedok vozacSvedok);
    PutnikSvedok findById(Long id);
    void deleteById(Long id);
    @Override
    List<PutnikSvedok> findAll();
    List<PutnikSvedok> findAllByPutnikIzvestajId(Long id);
}
