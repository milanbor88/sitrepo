package com.example.service;

import com.example.model.PovredjeniVanVozila;
import com.example.model.PovredjeniVaseVozilo;

import java.util.List;

public interface PovredjeniVanVozilaService {

    public void savePovredjeniVanVozila(PovredjeniVanVozila povredjeniVanVozila);
    List<PovredjeniVanVozila> findPvanByVozacNezgodaIzvestajId(Long id);
}
