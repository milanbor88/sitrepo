package com.example.repository;

import com.example.model.TipKlijenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TipKlijentaRepository extends JpaRepository<TipKlijenta, Long> {

    public List<TipKlijenta> findAll();
}
