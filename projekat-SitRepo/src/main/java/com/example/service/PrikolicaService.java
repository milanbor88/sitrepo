package com.example.service;

import com.example.model.Osiguranje;
import com.example.model.Prikolica;
import com.example.model.User;
import com.example.model.Vozilo;

import java.util.List;

public interface PrikolicaService {

    public void savePrikolica(Prikolica prikolica);
    public void updatePrikolica(Prikolica prikolica);
    public Prikolica findPrikolicaById(Long id);
    Prikolica findPrikolicaByUser(User user);
    void deletePrikolicaById (Long id);

    public List<Prikolica> findPrikolicaByStatus(String status);

}
