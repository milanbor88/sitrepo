package com.example.service;

import com.example.model.PovredjeniVaseVozilo;

import java.util.List;

public interface PovredjeniVaseVoziloService {

    public void savePovredjeniVaseVozilo(PovredjeniVaseVozilo povredjeniVaseVozilo);
    List<PovredjeniVaseVozilo> findPvvByVozacNezgodaIzvestajId(Long id);
}
