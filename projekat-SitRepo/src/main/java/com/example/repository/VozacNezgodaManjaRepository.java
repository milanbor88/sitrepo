package com.example.repository;

import com.example.model.User;
import com.example.model.VozacNezgodaManja;
import com.example.model.VozacNezgodaVeca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VozacNezgodaManjaRepository extends JpaRepository<VozacNezgodaManja, Long> {

    public VozacNezgodaManja findTopByUserOrderByIdDesc(User user);
    public List<VozacNezgodaManja> findByUser(User user);
    public  List<VozacNezgodaManja> findByPoslat(boolean poslat);
    public Page<VozacNezgodaManja> findByPoslat(boolean poslat, Pageable pageable);
}
