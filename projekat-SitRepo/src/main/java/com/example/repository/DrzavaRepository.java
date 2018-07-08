package com.example.repository;

import com.example.model.Drzave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrzavaRepository extends JpaRepository<Drzave, Long>{

    public List<Drzave> findAll();

}
