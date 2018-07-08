package com.example.service;

import com.example.model.Klijenti;
import com.example.model.TipKlijenta;

import java.util.List;

public interface TipKlijentaService {

    void save(TipKlijenta tipKlijenta);
    public List<TipKlijenta> findAll();

}
