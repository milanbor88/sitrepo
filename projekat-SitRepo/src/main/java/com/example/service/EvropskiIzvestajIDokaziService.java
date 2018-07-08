package com.example.service;

import com.example.model.EvropskiIzvestajIDokazi;

import java.util.List;

public interface EvropskiIzvestajIDokaziService {

    public EvropskiIzvestajIDokazi saveEvropskiIzvestajIDokazi (EvropskiIzvestajIDokazi evropskiIzvestajIDokazi);
    public EvropskiIzvestajIDokazi findEvropskiIzvestajIDokazikById(Long id);

    public List<EvropskiIzvestajIDokazi> findEvrDokaziByStatusAndVozacNezgodaManjaId(String status, Long id);
}
