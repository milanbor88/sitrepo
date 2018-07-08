package com.example.service;

import com.example.model.PovredjeniDrugoVozilo;

import java.util.List;

public interface PovredjeniDrugoVoziloService {

    public void savePovredjeniDrugoVozilo(PovredjeniDrugoVozilo povredjeniDrugoVozilo);
    List<PovredjeniDrugoVozilo> findPdvByVozacNezgodaIzvestajId(Long id);
}
