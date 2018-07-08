package com.example.service;

import com.example.dto.KlijentLtdLng;
import com.example.model.Klijenti;

import java.util.List;

public interface KlijentiService {


    void save(Klijenti klijenti);
    void delete(Klijenti klijenti);
    void update(Klijenti klijenti);
    void findAll();
    public Klijenti findByNameDrz(String name, String drzava);
    public List<Klijenti> findKlijentiByTipKlijenta(String tip);

    List<Klijenti> findKlientiAll();

    public void deleteKlientiById(Long id);

    public Klijenti findByName(String name);

    public Klijenti findByDrzava(String drzava);

    public List<KlijentLtdLng> getDistance(Double ltd, Double lng);

    public Klijenti findByPromoKod(String promoKod);

}
