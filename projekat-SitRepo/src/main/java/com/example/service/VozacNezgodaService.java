package com.example.service;

import com.example.model.VozacNezgoda;

import java.util.List;

public interface VozacNezgodaService {

    public VozacNezgoda saveVozacNezgoda(VozacNezgoda vozacNezgoda);

    List<VozacNezgoda> findVozacNezgodaAll();

}
