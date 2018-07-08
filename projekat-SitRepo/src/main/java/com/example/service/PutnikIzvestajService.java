package com.example.service;

import com.example.model.PutnikIzvestaj;
import com.example.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PutnikIzvestajService {

    public PutnikIzvestaj save(PutnikIzvestaj putnikIzvestaj);
    public List<PutnikIzvestaj> findAll();
    public PutnikIzvestaj findById(Long id);
    public PutnikIzvestaj topPutnikIzvestaj(User user);
    public List<PutnikIzvestaj> findByUser(User user);
    List<PutnikIzvestaj> findByPoslatPutnikIzv(boolean poslat);
    public Page<PutnikIzvestaj> findByPoslatPagePutnikIzv(boolean poslat, Pageable pageble);

}
