package com.example.service;

import com.example.model.PrikolicaB;
import com.example.model.VozacNezgodaManja;

import java.util.List;

public interface PrikolicaBService {

    public void savePrikolicaB(PrikolicaB prikolicaB);
    public void updatePrikolicaB(PrikolicaB prikolicaB);
    public PrikolicaB findPrikolicaBById(Long id);
    void deletePrikolicaBById(Long id);

    PrikolicaB findUOBByVozacNezgodaManjaId(Long id);
    PrikolicaB findByVozacNezgodaManja(VozacNezgodaManja manja);
}
