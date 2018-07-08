package com.example.repository;

import com.example.model.User;
import com.example.model.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoziloRepository extends JpaRepository<Vozilo, Long> {
    Vozilo findById(Long id);
    Vozilo findVoziloByUser_Id(Long id);
    void deleteById(Long id);

    Vozilo findVoziloByUser(User user);
}
