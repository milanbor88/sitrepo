package com.example.repository;

import com.example.model.Osiguranje;
import com.example.model.User;
import com.example.model.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OsiguranjeRepository extends JpaRepository<Osiguranje, Long> {

    Osiguranje findById(Long id);
    void deleteById (Long id);
    Osiguranje findOsiguranjeByUser(User user);

}
