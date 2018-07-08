package com.example.service;

import com.example.model.Ucesnik;
import com.example.model.VozacNezgodaManja;

import java.util.List;

public interface UcesnikService {

    public void saveUcesnik(Ucesnik ucesnik);
    void deleteUcesnikById(Long id);
    Ucesnik findUcesnikById(Long id);
    Ucesnik findUcesnikByVozacNezgodaManjaId(Long id);

    public Ucesnik findByVozacNezgodaManja(VozacNezgodaManja manja);

}
