package com.example.service;

import com.example.model.Osiguranje;
import com.example.model.OsiguranjeB;
import com.example.model.VozacNezgodaManja;

import java.util.List;

public interface OsiguranjeBService {

    public void saveOsiguranjeB(OsiguranjeB osiguranjeB);
    public void updateOsiguranjeB(OsiguranjeB osiguranjeB);
    public OsiguranjeB findOsiguranjeBById(Long id);
    void deleteOsiguranjeBById(Long id);
    List<OsiguranjeB> findUOBByVozacNezgodaManjaId(Long id);
    OsiguranjeB findByVozacNezgodaManja(VozacNezgodaManja manja);
}
