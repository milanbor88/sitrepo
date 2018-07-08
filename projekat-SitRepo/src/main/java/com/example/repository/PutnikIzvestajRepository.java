package com.example.repository;

import com.example.model.PutnikIzvestaj;
import com.example.model.User;
import com.example.model.VozacNezgodaIzvestaj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PutnikIzvestajRepository extends JpaRepository<PutnikIzvestaj, Long>,
        PagingAndSortingRepository<PutnikIzvestaj, Long> {

    public PutnikIzvestaj findTopByUserOrderByIdDesc(User user);
    List<PutnikIzvestaj> findByUser(User user);
    List<PutnikIzvestaj> findByPoslat(boolean poslat);
    public Page<PutnikIzvestaj> findByPoslat(boolean poslat, Pageable pageble);

}
