package com.example.repository;

import com.example.model.User;
import com.example.model.VozacNezgodaIzvestaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VozacNezgodaIzvestajRepository extends JpaRepository<VozacNezgodaIzvestaj, Long>,
        PagingAndSortingRepository<VozacNezgodaIzvestaj, Long> {

    @Transactional(readOnly =true)
    @Query("Select v from VozacNezgodaIzvestaj as v WHERE v.user = :user and v.id = (select max(vv.id) from VozacNezgodaIzvestaj as vv where vv.user= v.user)")
    public VozacNezgodaIzvestaj test( @Param("user") User user);



}
