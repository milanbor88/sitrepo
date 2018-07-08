package com.example.repository;

import com.example.model.User;
import com.example.model.VozacNezgodaVeca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface VozacNezgodaVecaRepository extends JpaRepository<VozacNezgodaVeca, Long>,PagingAndSortingRepository<VozacNezgodaVeca, Long> {

    public VozacNezgodaVeca findTopByUserOrderByIdDesc(User user);
    public Page<VozacNezgodaVeca> findByPoslat(boolean poslat, Pageable pageable);
    List<VozacNezgodaVeca> findByUser(User user);
    public List<VozacNezgodaVeca> findByPoslat(boolean poslat);


}
