package com.example.repository;

import com.example.model.Prikolica;
import com.example.model.User;
import com.example.model.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrikolicaRepository extends JpaRepository<Prikolica, Long> {

    Prikolica findById(Long id);
    Prikolica findPrikolicaByUser(User user);
    void deleteById (Long id);

    List<Prikolica> findByStatus(String status);
}
