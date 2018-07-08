package com.example.service;

import com.example.model.PutnikSvedok;
import com.example.model.User;

import java.util.List;

public interface PutnikSvedokService {

    public void savePutnikSvedok (PutnikSvedok putnikSvedok);
    public void deletePutnikSvedok(Long id);
    public PutnikSvedok findPutnikSvedokById(Long id);

    List<PutnikSvedok> findPutnikSvedokAll();
    List<PutnikSvedok> findPutnikSvedokByPutnikIzvestajId(Long id);
}
