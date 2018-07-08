package com.example.service;

import com.example.model.VozacNezgodaManja;
import com.example.model.Vozilo;
import com.example.model.VoziloB;

import java.util.List;

public interface VoziloBService {

    public void saveVoziloB(VoziloB voziloB);
    public void updateVoziloB(VoziloB voziloB);
    public VoziloB findVoziloBById(Long id);
    void deleteVoziloBById(Long id);
    VoziloB findUOBByVozacNezgodaManjaId(Long id);
    VoziloB findByVozacNezgodaManja(VozacNezgodaManja manja);

}
