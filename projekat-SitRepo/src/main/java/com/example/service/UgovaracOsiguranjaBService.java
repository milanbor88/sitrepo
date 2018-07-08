package com.example.service;

import com.example.model.UgovaracOsiguranja;
import com.example.model.UgovaracOsiguranjaB;
import com.example.model.VozacNezgodaManja;

import java.util.List;

public interface UgovaracOsiguranjaBService {

    public void saveUgovaracOsigruranjaB(UgovaracOsiguranjaB ugovaracOsiguranjaB);
    public void updateUgovaracB(UgovaracOsiguranjaB ugovaracOsiguranjaB);
    public UgovaracOsiguranjaB findUgovaracOsiguranjaBById(Long id);
    void deleteUgovaracOsiguranjaBById(Long id);
    UgovaracOsiguranjaB findUOBByVozacNezgodaManjaId(Long id);
    UgovaracOsiguranjaB findByVozacNezgodaManja(VozacNezgodaManja manja);

}
