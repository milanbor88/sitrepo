package com.example.service;

import com.example.model.OkolnostiNezgode;
import com.example.model.User;
import com.example.model.VozacNezgodaIzvestaj;
import com.example.model.VozacNezgodaManja;

public interface OkolnostiNezgodeService {

    public void saveOkolnostiNezgode (OkolnostiNezgode okolnostiNezgode);
    OkolnostiNezgode findOkolnostiNezgodeById(Long id);
    public void updateOkolnostiNezgode(OkolnostiNezgode okolnostiNezgode);
    public OkolnostiNezgode findNezgodaByManja(VozacNezgodaManja manja);

}
