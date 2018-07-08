package com.example.service;

import com.example.model.OkolnostiNezgodeB;
import com.example.model.VozacNezgodaManja;

public interface OkolnostiNezgodeBService {

    public void saveOkolnostiNezgodeB(OkolnostiNezgodeB okolnostiNezgodeB);
    OkolnostiNezgodeB findOkolnostiNezgodeBById(Long id);
    public void updateOkolnostiNezgodeB(OkolnostiNezgodeB okolnostiNezgodeB);
    public OkolnostiNezgodeB findNezgodaBByManja(VozacNezgodaManja manja);
}
